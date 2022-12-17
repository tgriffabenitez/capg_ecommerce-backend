package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> obtenerUnaCategoria() {
        return new ResponseEntity<>(categoriaRepository.findAll(), HttpStatus.OK);
    }

}
