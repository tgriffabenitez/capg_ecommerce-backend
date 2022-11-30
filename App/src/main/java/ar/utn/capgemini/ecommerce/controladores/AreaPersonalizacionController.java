package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.repositorios.AreaPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/areaPersonalizacion")
public class AreaPersonalizacionController {

    @Autowired
    public AreaPersonalizacionRepository areaPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public Page<AreaPersonalizacion> obtenerArea(Pageable pagina){
        return areaPersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{areaPersonalizacionId}"})
    public Optional<AreaPersonalizacion> obtenerAreaId(@PathVariable("areaPersonalizacionId") Integer id){
        return areaPersonalizacionRepository.findById(id);
    }

    @PostMapping(path = {"", "/"})
    public AreaPersonalizacion agregarArea(@RequestBody @Validated AreaPersonalizacion area) {
        if (areaPersonalizacionRepository.existsByArea(area.getArea())) {
            return areaPersonalizacionRepository.findByArea(area.getArea());
        }
        return areaPersonalizacionRepository.save(area);
    }

    @DeleteMapping(path = {"/{areaPersonalizacionId}"})
    public void borrarAreaPersonalizacionId(@PathVariable("areaPersonalizacionId") Integer id){
        areaPersonalizacionRepository.deleteById(id);
    }

    @PutMapping(path = {"/{areaPersonalizacionId}"})
    public AreaPersonalizacion actualizarAreaPersonalizacion(@PathVariable("areaPersonalizacionId") @RequestBody @Validated Integer id, AreaPersonalizacion areaPersonalizacion){
        areaPersonalizacion.setId(id);
        areaPersonalizacionRepository.save(areaPersonalizacion);
        return areaPersonalizacion;
    }

}
