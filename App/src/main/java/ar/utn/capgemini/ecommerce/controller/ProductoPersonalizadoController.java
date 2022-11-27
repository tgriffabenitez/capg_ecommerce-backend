package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.repository.ProductoPersonalizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productoPersonalizado")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;

    @GetMapping(path = {"", "/"})
    public List<ProductoPersonalizado> obtenerProductosPersonalizados(){
        return productoPersonalizadoRepository.findAll();
    }

    @GetMapping(path = {"/{productoPersonalizadoId}"})
    public Optional<ProductoPersonalizado> obtenerProductoPersonalizadoId(@PathVariable("productoPersonalizadoId") Integer productoPersonalizadoId){
        return productoPersonalizadoRepository.findById(productoPersonalizadoId);
    }

    @PostMapping(path = {"", "/"})
    public ProductoPersonalizado agregarProductoPersonalizado(@RequestBody ProductoPersonalizado productoPersonalizado){
        return productoPersonalizadoRepository.save(productoPersonalizado);
    }

    @DeleteMapping(path = {"/{productoPersonalizadoId}"})
    public void  borrarProductoPersonalizadoId(@PathVariable("productoPersonalizadoId") Integer productoPersonalizadoId){
        productoPersonalizadoRepository.deleteById(productoPersonalizadoId);
    }

    @PutMapping(path = {"/{productoPersonalizado}"})
    public ProductoPersonalizado actualizarProductoBase(@PathVariable("prodcutoPersonalizadoId") @RequestBody Integer id, ProductoPersonalizado productoPersonalizado){
        productoPersonalizado.setId(id);
        productoPersonalizadoRepository.save(productoPersonalizado);
        return productoPersonalizado;
    }

}
