package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.repository.AreaPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/areasPersonalizacion")
public class AreaPersonalizacionController {

    @Autowired
    public AreaPersonalizacionRepository areaPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public List<AreaPersonalizacion> obtenerArea(){
        return areaPersonalizacionRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public AreaPersonalizacion agregarArea(@RequestBody AreaPersonalizacion area){
        return areaPersonalizacionRepository.save(area);
    }

}
