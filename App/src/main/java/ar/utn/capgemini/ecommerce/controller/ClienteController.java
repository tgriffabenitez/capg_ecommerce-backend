package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.ClienteDTO;
import ar.utn.capgemini.ecommerce.model.entities.Cliente;
import ar.utn.capgemini.ecommerce.repository.ClienteRepository;
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
    private ClienteRepository clienteRepository;

    @GetMapping(path = "")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<?> crearCliente(@RequestBody @Valid ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            boolean existeMail = clienteRepository.existsByEmail(clienteDTO.getEmail());
            if (!existeMail) {
                Cliente cliente = new Cliente();
                cliente.setNombre(clienteDTO.getNombre());
                cliente.setApellido(clienteDTO.getApellido());
                cliente.setEmail(clienteDTO.getEmail());
                cliente.setTelefono(clienteDTO.getTelefono());
                cliente.setContrasenia(clienteDTO.getContrasenia());
                cliente.setDireccionCalle(clienteDTO.getDireccionCalle());
                cliente.setDireccionNumero(clienteDTO.getDireccionNumero());
                cliente.setDireccionPiso(clienteDTO.getDireccionPiso());
                cliente.setDireccionDepto(clienteDTO.getDireccionDepto());
                clienteRepository.save(cliente);
                return new ResponseEntity<>("Cliente creado con exito", HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("El mail ya esta registrado", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Error al crear el cliente", HttpStatus.BAD_REQUEST);
        }
    }
}

