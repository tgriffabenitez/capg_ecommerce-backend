package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.repository.ProductoPersonalizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/productoPersonalizado")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;

    @GetMapping(path = {"", "/"})
    public Page<ProductoPersonalizado> obtenerProductosPersonalizados(Pageable pagina){
        return productoPersonalizadoRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{productoPersonalizadoId}"})
    public Optional<ProductoPersonalizado> obtenerProductoPersonalizadoId(@PathVariable("productoPersonalizadoId") Integer id){
        return productoPersonalizadoRepository.findById(id);
    }

    // TODO: Falta hacer la logica para que no se repitan los id's
    @PostMapping(path = {"", "/"})
    public ProductoPersonalizado agregarProductoPersonalizado(@RequestBody @Validated ProductoPersonalizado productoPersonalizado){
        return productoPersonalizadoRepository.save(productoPersonalizado);
    }

    @DeleteMapping(path = {"/{productoPersonalizadoId}"})
    public void  borrarProductoPersonalizadoId(@PathVariable("productoPersonalizadoId") Integer id){
        productoPersonalizadoRepository.deleteById(id);
    }

    @PutMapping(path = {"/{productoPersonalizadoId}"})
    public ProductoPersonalizado actualizarProductoPersonalizado(@PathVariable("productoPersonalizadoId") @RequestBody @Validated
                                                                     Integer id, ProductoPersonalizado productoPersonalizado){
        productoPersonalizado.setId(id);
        productoPersonalizadoRepository.save(productoPersonalizado);
        return productoPersonalizado;
    }

}
