package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.ProductoBase;
import ar.utn.capgemini.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/productosBase")
public class ProductoBaseController {

    @Autowired
    public ProductoBaseRepository productoBaseRepository;

    @GetMapping(path = {"", "/"})
    public List<ProductoBase> obtenerProductosBase(){
        return productoBaseRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public ProductoBase agregarProductoBase(@RequestBody ProductoBase productoBase){
        return productoBaseRepository.save(productoBase);
    }

}
