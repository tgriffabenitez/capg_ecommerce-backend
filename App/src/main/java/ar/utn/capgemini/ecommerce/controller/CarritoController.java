package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.CompraDTO;
import ar.utn.capgemini.ecommerce.dto.PublicacionCarritoDTO;
import ar.utn.capgemini.ecommerce.model.Carrito;
import ar.utn.capgemini.ecommerce.model.PublicacionPorCarrito;
import ar.utn.capgemini.ecommerce.repository.CarritoRepository;
import ar.utn.capgemini.ecommerce.repository.PublicacionCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/carrito")
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private PublicacionCarritoRepository publicacionCarritoRepository;

    @GetMapping(path = {"/", ""})
    public ResponseEntity<?> obtenerCarritos() {
        return new ResponseEntity<>(carritoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = {"/", ""})
    public ResponseEntity<?> crearCarrito(@RequestBody CompraDTO compraDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        /*
         * Creo un carrito y le asigno el precioTotal (que viene por el dto)
         */
        Carrito carrito = new Carrito();
        carrito.setPrecioTotal(compraDTO.getTotal());
        /*
         * Recorro la lista de publicaciones que esta en el dto
         */
        for (PublicacionCarritoDTO publicacionPorCarritoDTO : compraDTO.getPublicaciones()) {
            /*
             * Verifico que exista la publicacion carrito, si no existe, termino la carga
             */
            if (!publicacionCarritoRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).isPresent())
                return new ResponseEntity<>("No existe la publicacion con id " + publicacionPorCarritoDTO.getPublicacionId(), HttpStatus.BAD_REQUEST);
            /*
             * En caso de que exista, obtengo la publicacionPorCarrito
             */
            PublicacionPorCarrito publicacionPorCarrito = publicacionCarritoRepository.findById(publicacionPorCarritoDTO.getPublicacionId()).get();
            /*
             * A la publicacionPorCarrito existente, le cargo la cantidad (cuantos "items" hay de esa publicacionPorCarrito)
             * y le cargo el subtotal (precioUnitario * cantidad) esto lo hace Angular
             */
            publicacionPorCarrito.setCantidad(publicacionPorCarritoDTO.getCantidad());
            publicacionPorCarrito.setSubtotal(publicacionPorCarritoDTO.getSubtotal());

            /*
             * Por ultimo, agrego la publicacionPorCarrito a la lista de publicaciones que tiene el carrito.
             * Esto lo hago con el metodo que cree yo
             */
            carrito.agregarPublicacion(publicacionPorCarrito);
        }
        carritoRepository.save(carrito);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
