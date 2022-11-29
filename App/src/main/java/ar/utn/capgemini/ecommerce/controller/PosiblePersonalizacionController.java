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

    // FIXME: Buscar la forma en que en caso de existir el tipo y area no genere un nuevo id la posiblePersonalizacion
    @PostMapping(path = {"", "/"})
    public PosiblePersonalizacion agregarPosiblePersonalizacion(@RequestBody PosiblePersonalizacion posiblePersonalizacion){
        TipoPersonalizacion tipoPersonalizacion = posiblePersonalizacion.getTipoPersonalizacion();
        AreaPersonalizacion areaPersonalizacion = posiblePersonalizacion.getAreaPersonalizacion();
        boolean existeTipo = tipoPersonalizacionRepository.existsByDescripcion(tipoPersonalizacion.getDescripcion());
        boolean existeArea = tipoPersonalizacionRepository.existsByDescripcion(areaPersonalizacion.getDescripcion());
        if(existeTipo && existeArea){
            return  posiblePersonalizacionRepository.findByTipoPersonalizacionAndAreaPersonalizacion(tipoPersonalizacion, areaPersonalizacion);
        }

        return posiblePersonalizacionRepository.save(posiblePersonalizacion);
    }

    @DeleteMapping(path = {"/{posiblePersonalizacionId}"})
    public void  borrarPosiblePersonalizacionId(@PathVariable("posiblePersonalizacionId") Integer id){
        posiblePersonalizacionRepository.deleteById(id);
    }

    @PutMapping(path = {"/{posiblePersonalizacionId}"})
    public PosiblePersonalizacion actualizarPosiblePersonalizacion(@PathVariable("posiblePersonalizacionId") @RequestBody Integer id, PosiblePersonalizacion posiblePersonalizacion){
        posiblePersonalizacion.setId(id);
        posiblePersonalizacionRepository.save(posiblePersonalizacion);
        return posiblePersonalizacion;
    }

}
