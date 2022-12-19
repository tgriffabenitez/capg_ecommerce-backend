package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.Vendedor;
import ar.utn.capgemini.ecommerce.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> obtenerVendedores() {
        return new ResponseEntity<>(vendedorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/metodos-de-pago/{vendedorId}"})
    public ResponseEntity<?> obtenerVendedorId(@PathVariable("vendedorId") Integer id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        if (vendedor == null)
            return new ResponseEntity<>("No existe el vendedor con ese id.", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(vendedor.getMetodosDePago(), HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public Vendedor agregarVendedor(@RequestBody @Validated Vendedor vendedor) {
        if (vendedorRepository.existsByTienda(vendedor.getTienda())) {
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
