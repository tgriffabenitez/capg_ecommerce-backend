package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.CompraDTO;
import ar.utn.capgemini.ecommerce.dto.PublicacionCarritoDTO;
import ar.utn.capgemini.ecommerce.model.Carrito;
import ar.utn.capgemini.ecommerce.model.Publicacion;
import ar.utn.capgemini.ecommerce.model.PublicacionPorCarrito;
import ar.utn.capgemini.ecommerce.repository.CarritoRepository;
import ar.utn.capgemini.ecommerce.repository.PublicacionCarritoRepository;
import ar.utn.capgemini.ecommerce.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/carrito")
public class CarritoController {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private PublicacionCarritoRepository publicacionCarritoRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

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
        return new ResponseEntity<>("carrito creado con exito", HttpStatus.CREATED);
    }
} // fin carritoController
