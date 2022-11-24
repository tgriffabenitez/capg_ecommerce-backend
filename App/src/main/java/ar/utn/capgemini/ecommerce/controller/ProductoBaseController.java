package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoBase;
import ar.utn.capgemini.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoBaseController {

    @Autowired
    private ProductoBaseRepository repo;

    @GetMapping(path = {"", "/"})
    public List<ProductoBase> traerProductos(){
        return repo.findAll();
    }

    @GetMapping(path = {"/{productoId}"})
    public ProductoBase traerProducto(@PathVariable("productoId") int id){
        Optional<ProductoBase> findById = repo.findById(id);
        return findById.orElse(null);
    }


    @DeleteMapping(path = "/{productoId}")
    public String borrarProducto(@PathVariable("productoId") int id){
        repo.deleteById(id);
        return "Producto borrado.";
    }

    @PostMapping(path = {"", "/"})
    public ProductoBase agregarProductoBase(@RequestBody ProductoBase productoBase){
        return repo.save(productoBase);
    }

}