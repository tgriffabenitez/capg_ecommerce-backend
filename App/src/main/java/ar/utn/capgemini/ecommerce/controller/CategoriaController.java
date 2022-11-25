package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.Categoria;
import ar.utn.capgemini.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "categorias")
public class CategoriaController {

    @Autowired
    public CategoriaRepository categoriaRepository;

    @GetMapping(path = {"", "/"})
    public List<Categoria> obtenerCategoria(){
        return categoriaRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public Categoria agregarCategoria(@RequestBody Categoria categoria){
        return categoriaRepository.save(categoria);
    }

}
