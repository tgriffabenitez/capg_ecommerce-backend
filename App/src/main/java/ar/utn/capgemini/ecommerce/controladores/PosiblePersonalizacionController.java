package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PosiblePersonalizacion;
import ar.utn.capgemini.ecommerce.modelo.entidades.productos.TipoPersonalizacion;
import ar.utn.capgemini.ecommerce.repositorios.AreaPersonalizacionRepository;
import ar.utn.capgemini.ecommerce.repositorios.PosiblePersonalizacionRepository;
import ar.utn.capgemini.ecommerce.repositorios.TipoPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
    public Page<PosiblePersonalizacion> obtenerPosiblePersonalizacion(Pageable pagina){
        return posiblePersonalizacionRepository.findAll(pagina);
    }
    @GetMapping(path = {"/{posiblePersonalizacionId}"})
    public Optional<PosiblePersonalizacion> obtenerPosiblePersonalizacion(@PathVariable("posiblePersonalizacionId") Integer id){
        return posiblePersonalizacionRepository.findById(id);
    }

    // FIXME: Buscar la forma en que en caso de existir el tipo y area no genere un nuevo id la posiblePersonalizacion
    @PostMapping(path = {"", "/"})
    public PosiblePersonalizacion agregarPosiblePersonalizacion(@RequestBody @Validated PosiblePersonalizacion posiblePersonalizacion){
        TipoPersonalizacion tipoPersonalizacion = posiblePersonalizacion.getTipoPersonalizacion();
        AreaPersonalizacion areaPersonalizacion = posiblePersonalizacion.getAreaPersonalizacion();
        boolean existeTipo = tipoPersonalizacionRepository.existsByTipo(tipoPersonalizacion.getTipo());
        boolean existeArea = tipoPersonalizacionRepository.existsByTipo(areaPersonalizacion.getArea());
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
    public PosiblePersonalizacion actualizarPosiblePersonalizacion(@PathVariable("posiblePersonalizacionId") @RequestBody @Validated
                                                                        Integer id,
                                                                        PosiblePersonalizacion posiblePersonalizacion){
        posiblePersonalizacion.setId(id);
        posiblePersonalizacionRepository.save(posiblePersonalizacion);
        return posiblePersonalizacion;
    }

}
