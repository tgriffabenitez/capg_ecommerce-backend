package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.Vendedor;
import ar.utn.capgemini.ecommerce.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping(path = {"", "/"})
    public List<Vendedor> obtenerVendedores(){
        return vendedorRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public Vendedor agregarVendedor(@RequestBody Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

}
