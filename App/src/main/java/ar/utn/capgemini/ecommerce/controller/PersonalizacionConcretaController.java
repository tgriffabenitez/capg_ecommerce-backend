package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.PersonalizacionConcreta;
import ar.utn.capgemini.ecommerce.repository.PersonalizacionConcretaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/personalizacionConcreta")
public class PersonalizacionConcretaController {

    @Autowired
    private PersonalizacionConcretaRepository personalizacionConcretaRepository;

    @GetMapping(path = {"", "/"})
    public List<PersonalizacionConcreta> obtenerPersonalizacionConcreta(){
        return personalizacionConcretaRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public PersonalizacionConcreta agregarPersonalizacionConcreta(@RequestBody PersonalizacionConcreta personalizacionConcreta){
        return personalizacionConcretaRepository.save(personalizacionConcreta);
    }

}