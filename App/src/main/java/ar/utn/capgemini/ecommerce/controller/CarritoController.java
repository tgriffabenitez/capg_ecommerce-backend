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
        carrito.setPrecioTotal(compraDTO.getTotal());

        for (PublicacionCarritoDTO publicacionPorCarritoDTO : compraDTO.getPublicaciones()) {
            if (!publicacionRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).isPresent())
                return new ResponseEntity<>("No existe la publicacion con id " + publicacionPorCarritoDTO.getPublicacionId(), HttpStatus.BAD_REQUEST);

            Publicacion publicacion = publicacionRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).get();

            PublicacionPorCarrito publicacionPorCarrito = new PublicacionPorCarrito();
            publicacionPorCarrito.setPublicacion(publicacion);
            publicacionPorCarrito.setCantidad(publicacionPorCarritoDTO.getCantidad());
            publicacionPorCarrito.setSubtotal(publicacionPorCarritoDTO.getSubtotal());
            publicacionCarritoRepository.save(publicacionPorCarrito);

            carrito.agregarPublicacion(publicacionPorCarrito);
        }

        carritoRepository.save(carrito);


        // Verifico si el cliente existe
        if (!clienteRepository.findById(compraDTO.getClienteId()).isPresent()) {
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

            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setMetodoDePago(compraDTO.getMetodoDePago());
            compra.setCarrito(carrito);
            compra.setPrecioTotal(compraDTO.getTotal());
            compra.setFechaDeCompra(LocalDateTime.now());
            compraRepository.save(compra);
        } else {
            // Si existe, lo busco
            Cliente cliente = clienteRepository.findById(compraDTO.getClienteId()).get();
            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setMetodoDePago(compraDTO.getMetodoDePago());
            compra.setCarrito(carrito);
            compra.setPrecioTotal(compraDTO.getTotal());
            compra.setFechaDeCompra(LocalDateTime.now());
            compraRepository.save(compra);
        }

        return new ResponseEntity<>("carrito creado con exito", HttpStatus.CREATED);
    }
} // fin carritoController
