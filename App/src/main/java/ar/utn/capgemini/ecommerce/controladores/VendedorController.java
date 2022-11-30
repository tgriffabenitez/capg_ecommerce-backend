package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.personas.Vendedor;
import ar.utn.capgemini.ecommerce.repositorios.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping(path = {"", "/"})
    public List<Vendedor> obtenerVendedores(){
        return vendedorRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public Vendedor agregarVendedor(@RequestBody @Validated Vendedor vendedor){
        String nombreTienda = vendedor.getTienda();
        boolean existeTienda = vendedorRepository.existsByTienda(nombreTienda);
        if(existeTienda){
            return vendedorRepository.findByTienda(nombreTienda);
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
