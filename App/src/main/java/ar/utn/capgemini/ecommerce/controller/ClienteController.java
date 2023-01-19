package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.ClienteDTO;
import ar.utn.capgemini.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "")
    public ResponseEntity<?> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable("id") Integer id) {
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(path = "/{id}/compras")
    public ResponseEntity<?> obtenerComprasCliente(@PathVariable Integer id) {
        return clienteService.obtenerCompras(id);
    }

    @PostMapping(path = {""})
    public ResponseEntity<?> registrarCliente(@RequestBody @Valid ClienteDTO clienteDTO, BindingResult bindingResult) {
        return clienteService.registrarCliente(clienteDTO, bindingResult);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable("id") Integer id, @RequestBody @Valid ClienteDTO clienteDTO, BindingResult bindingResult) {
        return clienteService.modificarCliente(id, clienteDTO, bindingResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> darClienteDeBaja(@PathVariable("id") Integer id) {
        return clienteService.darDeBaja(id);
    }

} // fin ClienteController



