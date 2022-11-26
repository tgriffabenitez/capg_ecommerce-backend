package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.MetodoDePago;
import ar.utn.capgemini.ecommerce.repository.MetodoDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/metodosDePago")
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    @GetMapping(path = {"", "/"})
    public List<MetodoDePago> obtenerMetodoDePago(){
        return metodoDePagoRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public void agregarMetodoDePago(@RequestBody MetodoDePago metodoDePago){
        metodoDePagoRepository.save(metodoDePago);
    }

}
