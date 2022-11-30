package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.publicaciones.MetodoDePago;
import ar.utn.capgemini.ecommerce.repositorios.MetodoDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/metodosDePago")
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    @GetMapping(path = {"", "/"})
    public Page<MetodoDePago> obtenerMetodoDePago(Pageable pagina){
        return metodoDePagoRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{metodosDePagoId}"})
    public Optional<MetodoDePago> obtenerMetodoDePago(@PathVariable("metodosDePagoId") Integer id){
        return metodoDePagoRepository.findById(id);
    }

    @PostMapping(path = {"", "/"})
    public MetodoDePago agregarMetodoDePago(@RequestBody @Validated MetodoDePago metodoDePago){
        if(metodoDePagoRepository.existsByFormaDePago(metodoDePago.getFormaDePago())){
            return metodoDePagoRepository.findByFormaDePago(metodoDePago.getFormaDePago());
        }
        return metodoDePagoRepository.save(metodoDePago);
    }

    @DeleteMapping(path = {"/{metosDePagoId}"})
    public void  borrarMetodoDePagoId(@PathVariable("metosDePagoId") Integer id){
        metodoDePagoRepository.deleteById(id);
    }

    @PutMapping(path = {"/{metodosDePagoId}"})
    public MetodoDePago actualizarMetodoDePago(@PathVariable("metodosDePagoId") @RequestBody @Validated Integer id, MetodoDePago metodoDePago){
        metodoDePago.setId(id);
        metodoDePagoRepository.save(metodoDePago);
        return metodoDePago;
    }

}
