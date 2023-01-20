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
import java.util.List;


@RestController
@RequestMapping(path = "/carrito")
public class CarritoController {
    @Autowired
    private PublicacionCarritoRepository publicacionCarritoRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCompra(@PathVariable Integer id) {
        Compra compra = compraRepository.findById(id).orElse(null);

        if (compra == null)
            return new ResponseEntity<>("No existe el carrito", HttpStatus.NOT_FOUND);

        // de esta forma, sabiendo el id del carrito puedo traer todas las publicaciones que tiene
        // y mostrar el detalle de la compra y el historial
        List<PublicacionPorCarrito> publicacionCarritos = compra.getPublicacionesPorCarrito();
        return new ResponseEntity<>(publicacionCarritos, HttpStatus.OK);
    }

    @PostMapping(path = {"/", ""})
    public ResponseEntity<?> crearCarrito(@RequestBody @Valid CompraDTO compraDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        // Genero una nueva compra
        Compra compra = new Compra();
        Double precioTotal = 0.0;

        // Recorro la lista de publicaciones que me llega en el DTO y las agrego al carrito
        for (PublicacionCarritoDTO publicacionPorCarritoDTO : compraDTO.getPublicaciones()) {
            if (publicacionRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).isEmpty())
                return new ResponseEntity<>("No existe la publicacion con id " + publicacionPorCarritoDTO.getPublicacionId(), HttpStatus.BAD_REQUEST);

            // busco la publicacion por id y el metodo de pago
            Publicacion publicacion = publicacionRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).get();
            MetodoDePago metodoDePago = metodoDePagoRepository.findById(compraDTO.getMetodoDePago()).orElse(null);
            if (metodoDePago == null)
                return new ResponseEntity<>("No existe el metodo de pago con id " + compraDTO.getMetodoDePago(), HttpStatus.BAD_REQUEST);

            // de la publicacion, obtengo el precio del producto personalizado
            ProductoPersonalizado productoPersonalizado = publicacion.getProductoPersonalizado();
            Double precioProductoPersonalizado = productoPersonalizado.calcularPrecioTotal();

            // Creo la publicacionPorCarrito y le seteo los atributos
            PublicacionPorCarrito publicacionPorCarrito = new PublicacionPorCarrito();
            publicacionPorCarrito.setPublicacion(publicacion);
            publicacionPorCarrito.setCantidad(publicacionPorCarritoDTO.getCantidad());
            publicacionPorCarrito.setPrecioUnitario(precioProductoPersonalizado);

            // el subtotal de la publicacionPorCarrito es el precio del productoPersonalizado x la cantidad
            publicacionPorCarrito.setSubtotal(precioProductoPersonalizado * publicacionPorCarritoDTO.getCantidad());
            publicacionCarritoRepository.save(publicacionPorCarrito);

            compra.agregarPublicacion(publicacionPorCarrito);
            precioTotal += publicacionPorCarrito.getSubtotal();
        }

        // Al carrito creado le seteo el precio total y lo guardo
        compra.setPrecioTotal(precioTotal);
        compraRepository.save(compra);

        // verifico que exista el cliente
        Cliente cliente = clienteRepository.findById(compraDTO.getClienteId()).orElse(null);
        if (cliente == null)
            return new ResponseEntity<>("No existe el cliente con id " + compraDTO.getClienteId(), HttpStatus.BAD_REQUEST);

        // verifico el id del metodo de pago
        if (metodoDePagoRepository.findById(compraDTO.getMetodoDePago()).isEmpty())
            return new ResponseEntity<>("No existe el metodo de pago con id " + compraDTO.getMetodoDePago(), HttpStatus.BAD_REQUEST);

        // creo una nueva compra, le guardo los atributos y la guardo
        compra.setCliente(cliente);
        compra.setMetodoDePago(metodoDePagoRepository.findById(compraDTO.getMetodoDePago()).get().getFormaDePago());
        compra.setPrecioTotal(compra.getPrecioTotal());
        compra.setFechaDeCompra(LocalDateTime.now());
        cliente.agregarCompra(compra);
        compraRepository.save(compra);

        return new ResponseEntity<>("carrito creado con exito", HttpStatus.CREATED);
    } // fin crearCarrito

} // fin carritoController
