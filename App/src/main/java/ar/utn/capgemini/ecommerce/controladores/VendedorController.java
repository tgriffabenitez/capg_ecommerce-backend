package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.personas.Vendedor;
import ar.utn.capgemini.ecommerce.repositorios.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping(path = {"", "/"})
    public Page<Vendedor> obtenerVendedores(Pageable pagina){
        return vendedorRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{vendedorId}"})
    public Optional<Vendedor> obtenerVendedorId(@PathVariable("vendedorId") Integer id){
        return vendedorRepository.findById(id);
    }

    @PostMapping(path = {"", "/"})
    public Vendedor agregarVendedor(@RequestBody @Validated Vendedor vendedor){
        if(vendedorRepository.existsByTienda(vendedor.getTienda())){
            return vendedorRepository.findByTienda(vendedor.getTienda());
        }
        return vendedorRepository.save(vendedor);
    }

    @DeleteMapping(path = {"/{vendedorId}"})
    public void  borrarTipoVendedorId(@PathVariable("vendedorId") Integer id){
        vendedorRepository.deleteById(id);
    }

    @PutMapping(path = {"/{vendedorId}"})
    public Vendedor actualizarVendedor(@PathVariable("vendedorId") @RequestBody @Validated Integer id, Vendedor vendedor){
        vendedor.setId(id);
        vendedorRepository.save(vendedor);
        return vendedor;
    }

}
