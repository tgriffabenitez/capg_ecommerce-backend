package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.TipoPersonalizacion;
import ar.utn.capgemini.ecommerce.repository.TipoPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tipoPersonalizacion")
public class TipoPersonalizacionController {

    @Autowired
    public TipoPersonalizacionRepository tipoPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public List<TipoPersonalizacion> obtenerTipoPersonalizacion(){
        return tipoPersonalizacionRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public TipoPersonalizacion agregarTipoPersonalizacion(@RequestBody @Validated TipoPersonalizacion tipoPersonalizacion){
        String tipoIngresado = tipoPersonalizacion.getTipo();
        boolean existeArea = tipoPersonalizacionRepository.existsByTipo(tipoIngresado);
        if(existeArea){
            return tipoPersonalizacionRepository.findByTipo(tipoIngresado);
        }
        return tipoPersonalizacionRepository.save(tipoPersonalizacion);
    }

    @DeleteMapping(path = {"/{tipoPersonalizacionId}"})
    public void  borrarTipoPersonalizacionId(@PathVariable("tipoPersonalizacionId") Integer id){
        tipoPersonalizacionRepository.deleteById(id);
    }

    @PutMapping(path = {"/{tipoPersonalizacionId}"})
    public TipoPersonalizacion actualizarTipoPersonalizacion(@PathVariable("tipoPersonalizacionId") @RequestBody @Validated
                                                                 Integer id, TipoPersonalizacion tipoPersonalizacion){
        tipoPersonalizacion.setId(id);
        tipoPersonalizacionRepository.save(tipoPersonalizacion);
        return tipoPersonalizacion;
    }

}
