package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.CarritoDTO;
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
    public ResponseEntity<?> obtenerClientes() {
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<?> registrarCliente(@RequestBody @Valid ClienteDTO clienteDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (clienteRepository.existsByEmail(clienteDTO.getEmail()))
            return new ResponseEntity<>("El email ya esta registrado", HttpStatus.BAD_REQUEST);

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

        return new ResponseEntity<>("Cliente registrado con exito", HttpStatus.CREATED);
    }

    @PostMapping(path = "/{clienteId}/carrito")
    public ResponseEntity<?> crearCarrtio(@PathVariable Integer clienteId, @RequestBody @Valid CarritoDTO carritoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!clienteRepository.findById(clienteId).isPresent())
            return new ResponseEntity<>("El cliente no existe", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Carrito creado con exito", HttpStatus.CREATED);
    }
}

