package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.PosiblePersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.ProductoBase;
import ar.utn.capgemini.ecommerce.repository.PosiblePersonalizacionRepository;
import ar.utn.capgemini.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "posiblesPersonalizaciones")
public class PosiblePersonalizacionController {

    @Autowired
    public PosiblePersonalizacionRepository posiblePersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public List<PosiblePersonalizacion> obtenerPosiblePersonalizacion(){
        return posiblePersonalizacionRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public PosiblePersonalizacion agregarProductoBase(@RequestBody PosiblePersonalizacion posiblePersonalizacion){
        return posiblePersonalizacionRepository.save(posiblePersonalizacion);
    }

}
