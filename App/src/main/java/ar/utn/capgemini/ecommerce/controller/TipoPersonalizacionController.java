package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.TipoPersonalizacion;
import ar.utn.capgemini.ecommerce.repository.TipoPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TipoPersonalizacion agregarTipoPersonalizacion(@RequestBody TipoPersonalizacion tipoPersonalizacion){
        return tipoPersonalizacionRepository.save(tipoPersonalizacion);
    }

}
