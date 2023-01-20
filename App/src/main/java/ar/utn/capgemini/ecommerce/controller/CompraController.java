package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerCompraPorId(@PathVariable("id") Integer id) {
        return compraService.obtenerCompra(id);
    }
}
