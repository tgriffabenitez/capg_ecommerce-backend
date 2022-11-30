package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PosiblePersonalizacion;
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

    @PostMapping(path = {"", "/"})
    public PosiblePersonalizacion agregarPosiblePersonalizacion(@RequestBody @Validated PosiblePersonalizacion posiblePersonalizacion){
        Integer areaId = posiblePersonalizacion.getAreaPersonalizacion().getId();
        Integer tipoId = posiblePersonalizacion.getTipoPersonalizacion().getId();
        if (posiblePersonalizacionRepository.existsByAreaPersonalizacionIdAndTipoPersonalizacionId(areaId, tipoId)){
            return posiblePersonalizacionRepository.findByTipoPersonalizacionAndAreaPersonalizacion(posiblePersonalizacion.getTipoPersonalizacion(), posiblePersonalizacion.getAreaPersonalizacion());
        }
        return posiblePersonalizacionRepository.save(posiblePersonalizacion);
    }

    @DeleteMapping(path = {"/{posiblePersonalizacionId}"})
    public void  borrarPosiblePersonalizacionId(@PathVariable("posiblePersonalizacionId") Integer id){
        posiblePersonalizacionRepository.deleteById(id);
    }

    @PutMapping(path = {"/{posiblePersonalizacionId}"})
    public PosiblePersonalizacion actualizarPosiblePersonalizacion(@PathVariable("posiblePersonalizacionId") @RequestBody @Validated Integer id, PosiblePersonalizacion posiblePersonalizacion){
        posiblePersonalizacion.setId(id);
        posiblePersonalizacionRepository.save(posiblePersonalizacion);
        return posiblePersonalizacion;
    }

}
