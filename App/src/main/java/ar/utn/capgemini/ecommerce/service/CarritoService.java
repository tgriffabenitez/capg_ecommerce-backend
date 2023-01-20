package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.CompraDTO;
import ar.utn.capgemini.ecommerce.dto.PublicacionCarritoDTO;
import ar.utn.capgemini.ecommerce.model.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
public class CarritoService {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private PublicacionCarritoRepository publicacionCarritoRepository;
    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> generarCompra(CompraDTO compraDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Cliente cliente = clienteRepository.findById(compraDTO.getClienteId()).orElse(null);
        if (cliente == null)
            return new ResponseEntity<>("No se encontro el cliente", HttpStatus.NOT_FOUND);

        MetodoDePago metodoDePago = metodoDePagoRepository.findById(compraDTO.getMetodoDePago()).orElse(null);
        if (metodoDePago == null)
            return new ResponseEntity<>("No se encontro el metodo de pago", HttpStatus.NOT_FOUND);

        Double total = 0.0;

        // Creo una nueva compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        cliente.agregarCompra(compra);
        compra.setMetodoDePago(metodoDePago.getFormaDePago());
        compra.setFechaDeCompra(LocalDateTime.now());

        // Recorro la lista de publicaciones y las agrego al carrito
        for (PublicacionCarritoDTO publicacionCarritoDTO : compraDTO.getPublicaciones()) {
            Publicacion publicacion = publicacionRepository.findById(publicacionCarritoDTO.getPublicacionId()).orElse(null);
            if (publicacion == null)
                return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.NOT_FOUND);

            // De la publicacion obtengo el precio del producto personalizado
            ProductoPersonalizado productoPersonalizado = publicacion.getProductoPersonalizado();
            Double precioProductoPersonalizado = productoPersonalizado.calcularPrecioTotal();

            // Genero el listado de items del carrito
            PublicacionPorCarrito publicacionPorCarrito = new PublicacionPorCarrito();
            publicacionPorCarrito.setPublicacion(publicacion);
            publicacionPorCarrito.setCantidad(publicacionCarritoDTO.getCantidad());
            publicacionPorCarrito.setPrecioUnitario(precioProductoPersonalizado);

            // El subtotal de la publicacionCarrito es el precio del productoPersonalizado por la cantidad de productos
            publicacionPorCarrito.setSubtotal(precioProductoPersonalizado * publicacionCarritoDTO.getCantidad());
            publicacionCarritoRepository.save(publicacionPorCarrito);

            compra.agregarPublicacion(publicacionPorCarrito);
            total += publicacionPorCarrito.getSubtotal();
        }

        // Finalizada la lista de publicaciones, guardo el precio total en la compra
        compra.setPrecioTotal(total);
        compraRepository.save(compra);

        return new ResponseEntity<>("Compra creada con exito", HttpStatus.OK);
    }
}
