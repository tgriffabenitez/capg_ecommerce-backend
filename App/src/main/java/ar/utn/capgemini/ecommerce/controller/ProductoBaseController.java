package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoBase;
import ar.utn.capgemini.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/productosBase")
public class ProductoBaseController {

    @Autowired
    public ProductoBaseRepository productoBaseRepository;

    @GetMapping(path = {"", "/"})
    public List<ProductoBase> obtenerProductosBase(){
        return productoBaseRepository.findAll();
    }

    @GetMapping(path = {"/{productoBaseId}"})
    public Optional<ProductoBase> obtenerProductoBasePorId(@PathVariable("productoBaseId") Integer productoBaseId){
        return productoBaseRepository.findById(productoBaseId);
    }

    @PostMapping(path = {"", "/"})
    public ProductoBase agregarProductoBase(@RequestBody ProductoBase productoBase){
        return productoBaseRepository.save(productoBase);
    }

    @DeleteMapping(path = {"/{productoBaseId}"})
    public void  borrarProductoBaseId(@PathVariable("productoBaseId") Integer productoBaseId){
        productoBaseRepository.deleteById(productoBaseId);
    }

    @PutMapping(path = {"/{productoBaseId}"})
    public ProductoBase actualizarProductoBase(@PathVariable("productoBaseId") @RequestBody Integer id, ProductoBase productoBase){
        productoBase.setId(id);
        productoBaseRepository.save(productoBase);
        return productoBase;
    }

}
