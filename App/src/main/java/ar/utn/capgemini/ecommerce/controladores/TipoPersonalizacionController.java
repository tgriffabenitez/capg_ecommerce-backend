package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.TipoPersonalizacion;
import ar.utn.capgemini.ecommerce.repositorios.TipoPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "tipoPersonalizacion")
public class TipoPersonalizacionController {

    @Autowired
    public TipoPersonalizacionRepository tipoPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public Page<TipoPersonalizacion> obtenerTipoPersonalizacion(Pageable pagina){
        return tipoPersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{tipoPersonalizacionId}"})
    public Optional<TipoPersonalizacion> obtenerTipoId(@PathVariable("tipoPersonalizacionId") Integer id){
        return tipoPersonalizacionRepository.findById(id);
    }

    @PostMapping(path = {"", "/"})
    public TipoPersonalizacion agregarTipoPersonalizacion(@RequestBody @Validated TipoPersonalizacion tipoPersonalizacion){
        if(tipoPersonalizacionRepository.existsByTipo(tipoPersonalizacion.getTipo())){
            return tipoPersonalizacionRepository.findByTipo(tipoPersonalizacion.getTipo());
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
