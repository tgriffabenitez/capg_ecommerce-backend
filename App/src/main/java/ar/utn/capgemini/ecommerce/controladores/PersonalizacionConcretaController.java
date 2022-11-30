package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PersonalizacionConcreta;
import ar.utn.capgemini.ecommerce.repositorios.PersonalizacionConcretaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/personalizacionConcreta")
public class PersonalizacionConcretaController {

    @Autowired
    private PersonalizacionConcretaRepository personalizacionConcretaRepository;

    @GetMapping(path = {"", "/"})
    public Page<PersonalizacionConcreta> obtenerPersonalizacionConcreta(Pageable pagina){
        return personalizacionConcretaRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{personalizacionConcretaId}"})
    public Optional<PersonalizacionConcreta> obtenerPersonalizacionConcreta(@PathVariable("personalizacionConcretaId") Integer id){
        return personalizacionConcretaRepository.findById(id);
    }

    // TODO: Crear la logica para evitar que repitan de id los valores ingresados
    @PostMapping(path = {"", "/"})
    public PersonalizacionConcreta agregarPersonalizacionConcreta(@RequestBody @Validated PersonalizacionConcreta personalizacionConcreta){
        return personalizacionConcretaRepository.save(personalizacionConcreta);
    }

    @DeleteMapping(path = {"/{personalizacionConcretaId}"})
    public void  borrarPersonalizacionConcretaId(@PathVariable("personalizacionConcretaId") Integer id){
        personalizacionConcretaRepository.deleteById(id);
    }

    @PutMapping(path = {"/{personalizacionConcretaId}"})
    public PersonalizacionConcreta actualizarPersonalizacionConcreta(@PathVariable("personalizacionConcretaId") @RequestBody @Validated
                                                                         Integer id,
                                                                         PersonalizacionConcreta personalizacionConcreta){
        personalizacionConcreta.setId(id);
        personalizacionConcretaRepository.save(personalizacionConcreta);
        return personalizacionConcreta;
    }

}
