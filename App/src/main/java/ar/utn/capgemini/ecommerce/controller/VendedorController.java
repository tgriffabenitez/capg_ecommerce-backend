package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.VendedorDTO;
import ar.utn.capgemini.ecommerce.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping(path = "")
    public ResponseEntity<?> obtenerVendedores() {
        return vendedorService.listarVendedores();
    }

    @GetMapping(path = "/{vendedorId}")
    public ResponseEntity<?> obtenerVendedorId(@PathVariable("vendedorId") Integer id) {
        return vendedorService.obtenerVendedorPorId(id);
    }

    @GetMapping(path = "/{vendedorId}/metodos-de-pago")
    public ResponseEntity<?> obtenerMetodoDePago(@PathVariable("vendedorId") Integer id) {
        return vendedorService.obtenerMetodoDePago(id);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> agregarVendedor(@RequestBody @Valid VendedorDTO vendedorDTO, BindingResult bindingResult) {
        return vendedorService.agregarVendedor(vendedorDTO, bindingResult);
    }

    @DeleteMapping(path = {"/{vendedorId}"})
    public ResponseEntity<?> darVendedorDeBaja(@PathVariable("vendedorId") Integer id) {
        return vendedorService.darVendedorDeBaja(id);
    }

    @PatchMapping(path = {"/{vendedorId}"})
    public ResponseEntity<?> actualizarVendedor(@PathVariable("vendedorId") Integer id, @RequestBody @Valid VendedorDTO vendedorDTO, BindingResult bindingResult) {
        return vendedorService.modificarVendedor(id, vendedorDTO, bindingResult);
    }

}