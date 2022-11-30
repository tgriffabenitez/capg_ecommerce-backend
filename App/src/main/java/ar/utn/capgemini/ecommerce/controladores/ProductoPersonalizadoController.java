package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.repositorios.ProductoPersonalizadoRepository;
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
    public Optional<ProductoPersonalizado> obtenerProductoPersonalizadoId(@PathVariable("productoPersonalizadoId") Integer productoPersonalizadoId){
        return productoPersonalizadoRepository.findById(productoPersonalizadoId);
    }

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
