package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.entities.MetodoDePago;
import ar.utn.capgemini.ecommerce.model.enums.PAGO;
import ar.utn.capgemini.ecommerce.repository.MetodoDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/metodosDePago")
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    @GetMapping(path = {"", "/"})
    public List<MetodoDePago> obtenerMetodoDePago(){
        return metodoDePagoRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public MetodoDePago agregarMetodoDePago(@RequestBody MetodoDePago metodoDePago){
        PAGO formaDePago = metodoDePago.getFormaDePago();
        boolean existeMetodoDePago = metodoDePagoRepository.existsByFormaDePago(formaDePago);
        if(existeMetodoDePago){
            return metodoDePagoRepository.findByFormaDePago(formaDePago);
        }
        return metodoDePagoRepository.save(metodoDePago);
    }

    @DeleteMapping(path = {"/{metosDePagoId}"})
    public void  borrarMetodoDePagoId(@PathVariable("metosDePagoId") Integer id){
        metodoDePagoRepository.deleteById(id);
    }

    @PutMapping(path = {"/{metodosDePagoId}"})
    public MetodoDePago actualizarMetodoDePago(@PathVariable("metodosDePagoId") @RequestBody Integer id, MetodoDePago metodoDePago){
        metodoDePago.setId(id);
        metodoDePagoRepository.save(metodoDePago);
        return metodoDePago;
    }

}
