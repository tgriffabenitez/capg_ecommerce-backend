package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.ClienteDTO;
import ar.utn.capgemini.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> obtenerClientes() {
        return clienteService.findAll();
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<?> registrarCliente(@RequestBody @Valid ClienteDTO clienteDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (clienteService.findByEmail(clienteDTO.getEmail()).isPresent())
            return new ResponseEntity<>("El email ya esta registrado", HttpStatus.BAD_REQUEST);

        clienteService.agregarCliente(clienteDTO, clienteDTO.getEmail());
        return new ResponseEntity<>("Cliente registrado con exito", HttpStatus.CREATED);
    }
} // fin ClienteController



