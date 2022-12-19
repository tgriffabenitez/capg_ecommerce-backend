package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.Compra;
import ar.utn.capgemini.ecommerce.model.PublicacionPorCarrito;
import ar.utn.capgemini.ecommerce.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping(path = "/{compraId}")
    public ResponseEntity<?> obtenerCompraPorId(@PathVariable Integer compraId) {
        Compra compra = compraRepository.findById(compraId).orElse(null);

        if (compra == null)
            return new ResponseEntity<>("No existe la compra con ese id.", HttpStatus.NOT_FOUND);

        // obtengo la lista de publicacionesPorCarrito que tiene el id de carrito
        List<PublicacionPorCarrito> publicacionesPorCarrito = compra.getCarrito().getPublicacionesPorCarrito();
        return new ResponseEntity<>(publicacionesPorCarrito, HttpStatus.OK);
    }
}
