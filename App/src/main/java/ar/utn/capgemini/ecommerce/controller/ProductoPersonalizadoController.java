package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.repository.ProductoPersonalizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productoPersonalizado")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;

    @GetMapping(path = {"", "/"})
    public List<ProductoPersonalizado> obtenerProductosPersonalizados(){
        return productoPersonalizadoRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public ProductoPersonalizado agregarProductoPersonalizado(@RequestBody ProductoPersonalizado productoPersonalizado){
        return productoPersonalizadoRepository.save(productoPersonalizado);
    }

}
