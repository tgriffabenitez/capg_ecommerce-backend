package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoBase;
import ar.utn.capgemini.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/productosBase")
public class ProductoBaseController {

    @Autowired
    public ProductoBaseRepository productoBaseRepository;

    @GetMapping(path = {"", "/"})
    public Page<ProductoBase> obtenerProductosBase(Pageable pagina){
        return productoBaseRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{productoBaseId}"})
    public Optional<ProductoBase> obtenerProductoBasePorId(@PathVariable("productoBaseId") Integer productoBaseId){
        return productoBaseRepository.findById(productoBaseId);
    }

    // TODO: verificar que no se puedan introducir duplicados
    @PostMapping(path = {"", "/"})
    public ProductoBase agregarProductoBase(@RequestBody @Validated ProductoBase productoBase){
        return productoBaseRepository.save(productoBase);
    }

    @DeleteMapping(path = {"/{productoBaseId}"})
    public void  borrarProductoBaseId(@PathVariable("productoBaseId") Integer id){
        productoBaseRepository.deleteById(id);
    }

    @PutMapping(path = {"/{productoBaseId}"})
    public ProductoBase actualizarProductoBase(@PathVariable("productoBaseId") @RequestBody @Validated Integer id, ProductoBase productoBase){
        productoBase.setId(id);
        productoBaseRepository.save(productoBase);
        return productoBase;
    }

}
