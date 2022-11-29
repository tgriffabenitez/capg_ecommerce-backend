package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.repository.AreaPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/areaPersonalizacion")
public class AreaPersonalizacionController {

    @Autowired
    public AreaPersonalizacionRepository areaPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public List<AreaPersonalizacion> obtenerArea(){
        return areaPersonalizacionRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public AreaPersonalizacion agregarArea(@RequestBody @Validated AreaPersonalizacion area){
        String areaIngresada = area.getArea();
        boolean existeArea = areaPersonalizacionRepository.existsByArea(areaIngresada);
        if(existeArea){
            return areaPersonalizacionRepository.findByArea(areaIngresada);
        }
        return areaPersonalizacionRepository.save(area);
    }

    @DeleteMapping(path = {"/{areaPersonalizacionId}"})
    public void  borrarAreaPersonalizacionId(@PathVariable("areaPersonalizacionId") Integer id){
        areaPersonalizacionRepository.deleteById(id);
    }

    @PutMapping(path = {"/{areaPersonalizacionId}"})
    public AreaPersonalizacion actualizarAreaPersonalizacion(@PathVariable("areaPersonalizacionId") @RequestBody @Validated Integer id, AreaPersonalizacion areaPersonalizacion){
        areaPersonalizacion.setId(id);
        areaPersonalizacionRepository.save(areaPersonalizacion);
        return areaPersonalizacion;
    }

}
