package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.CompraDTO;
import ar.utn.capgemini.ecommerce.dto.PublicacionCarritoDTO;
import ar.utn.capgemini.ecommerce.model.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;


@RestController
@RequestMapping(path = "/carrito")
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private PublicacionCarritoRepository publicacionCarritoRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CompraRepository compraRepository;

    @GetMapping(path = {"/", ""})
    public ResponseEntity<?> obtenerCarritos() {
        return new ResponseEntity<>(carritoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = {"/", ""})
    public ResponseEntity<?> crearCarrito(@RequestBody @Valid CompraDTO compraDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Carrito carrito = new Carrito();
        Double precioTotal = 0.0;

        for (PublicacionCarritoDTO publicacionPorCarritoDTO : compraDTO.getPublicaciones()) {
            if (!publicacionRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).isPresent())
                return new ResponseEntity<>("No existe la publicacion con id " + publicacionPorCarritoDTO.getPublicacionId(), HttpStatus.BAD_REQUEST);

            Publicacion publicacion = publicacionRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).get();

            // Obtengo el precio del productoPersonalizado
            ProductoPersonalizado productoPersonalizado = publicacion.getProductoPersonalizado();
            Double precioProductoPersonalizado = productoPersonalizado.calcularPrecioTotal();

            PublicacionPorCarrito publicacionPorCarrito = new PublicacionPorCarrito();
            publicacionPorCarrito.setPublicacion(publicacion);
            publicacionPorCarrito.setCantidad(publicacionPorCarritoDTO.getCantidad());
            publicacionPorCarrito.setPrecioUnitario(precioProductoPersonalizado);

            // el subtotal de la publicacionPorCarrito es el precio del productoPersonalizado * la cantidad
            publicacionPorCarrito.setSubtotal(precioProductoPersonalizado * publicacionPorCarritoDTO.getCantidad());
            publicacionCarritoRepository.save(publicacionPorCarrito);

            carrito.agregarPublicacion(publicacionPorCarrito);
            precioTotal += publicacionPorCarrito.getSubtotal();
        }
        carrito.setPrecioTotal(precioTotal);
        carritoRepository.save(carrito);

        // Verifico si existe el clienteId
        if (compraDTO.getClienteId() == null) {
            // Si no existe, lo creo
            Cliente cliente = new Cliente();
            cliente.setNombre(compraDTO.getNombre());
            cliente.setApellido(compraDTO.getApellido());
            cliente.setEmail(compraDTO.getEmail());
            cliente.setTelefono(compraDTO.getTelefono());
            cliente.setDireccionCalle(compraDTO.getDireccionCalle());
            cliente.setDireccionNumero(compraDTO.getDireccionNumero());
            cliente.setDireccionPiso(compraDTO.getDireccionPiso());
            cliente.setDireccionDepto(compraDTO.getDireccionDepto());
            clienteRepository.save(cliente);

            // creo una nueva compra
            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setMetodoDePago(compraDTO.getMetodoDePago());
            compra.setCarrito(carrito);
            compra.setPrecioTotal(carrito.getPrecioTotal());
            compra.setFechaDeCompra(LocalDateTime.now());
            cliente.agregarCompra(compra);
            compraRepository.save(compra);
        } else {
            // Si el id no es nulo, vefifico que exista el cliente con ese id
            if (!clienteRepository.findById(compraDTO.getClienteId()).isPresent())
                return new ResponseEntity<>("No existe el cliente con id " + compraDTO.getClienteId(), HttpStatus.BAD_REQUEST);

            Cliente cliente = clienteRepository.findById(compraDTO.getClienteId()).get();

            // creo una nueva compra
            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setMetodoDePago(compraDTO.getMetodoDePago());
            compra.setCarrito(carrito);
            compra.setPrecioTotal(carrito.getPrecioTotal());
            compra.setFechaDeCompra(LocalDateTime.now());
            cliente.agregarCompra(compra);
            compraRepository.save(compra);
        }

        return new ResponseEntity<>("carrito creado con exito", HttpStatus.CREATED);
    }
} // fin carritoController
