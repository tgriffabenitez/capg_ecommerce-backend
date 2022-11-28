package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.PosiblePersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.TipoPersonalizacion;
import ar.utn.capgemini.ecommerce.repository.AreaPersonalizacionRepository;
import ar.utn.capgemini.ecommerce.repository.PosiblePersonalizacionRepository;
import ar.utn.capgemini.ecommerce.repository.TipoPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "posiblesPersonalizaciones")
public class PosiblePersonalizacionController {

    @Autowired
    public PosiblePersonalizacionRepository posiblePersonalizacionRepository;

    @Autowired
    public TipoPersonalizacionRepository tipoPersonalizacionRepository;

    @Autowired
    public AreaPersonalizacionRepository areaPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public List<PosiblePersonalizacion> obtenerPosiblePersonalizacion(){
        return posiblePersonalizacionRepository.findAll();
    }

    // FIXME: Buscar la forma en que en caso de existir el tipo y area no genere un nuevo id
    @PostMapping(path = {"", "/"})
    public PosiblePersonalizacion agregarProductoBase(@RequestBody PosiblePersonalizacion posiblePersonalizacion){
        TipoPersonalizacion tipoPersonalizacion = posiblePersonalizacion.getTipoPersonalizacion();
        AreaPersonalizacion areaPersonalizacion = posiblePersonalizacion.getAreaPersonalizacion();
        boolean existeTipo = tipoPersonalizacionRepository.existsByDescripcion(tipoPersonalizacion.getDescripcion());
        boolean existeArea = tipoPersonalizacionRepository.existsByDescripcion(areaPersonalizacion.getDescripcion());
        if(existeTipo && existeArea){
            return  posiblePersonalizacionRepository.findByTipoPersonalizacionAndAreaPersonalizacion(tipoPersonalizacion, areaPersonalizacion);
        }

        return posiblePersonalizacionRepository.save(posiblePersonalizacion);
    }

}
