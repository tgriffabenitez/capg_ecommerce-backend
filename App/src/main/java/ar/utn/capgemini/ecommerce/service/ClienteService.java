package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.ClienteDTO;
import ar.utn.capgemini.ecommerce.model.Cliente;
import ar.utn.capgemini.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> listarClientes() {
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null)
            return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerCompras(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null)
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cliente.getCompras(), HttpStatus.OK);
    }

    public ResponseEntity<?> registrarCliente(ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        boolean existeEmail = clienteRepository.existsByEmail(clienteDTO.getEmail());
        if (existeEmail)
            return new ResponseEntity<>("El email ya esta registrado", HttpStatus.CONFLICT);

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

        return new ResponseEntity<>("Usuario creado", HttpStatus.OK);
    }

    public ResponseEntity<?> modificarCliente(Integer id, ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null)
            return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);

        if (clienteDTO.getNombre() != null)
            cliente.setNombre(clienteDTO.getNombre());

        if (clienteDTO.getApellido() != null)
            cliente.setApellido(clienteDTO.getApellido());

        if (clienteDTO.getEmail() != null)
            cliente.setEmail(clienteDTO.getEmail());

        if (clienteDTO.getTelefono() != null)
            cliente.setTelefono(clienteDTO.getTelefono());

        if (clienteDTO.getContrasenia() != null)
            cliente.setContrasenia(clienteDTO.getContrasenia());

        if (clienteDTO.getDireccionCalle() != null)
            cliente.setDireccionCalle(clienteDTO.getDireccionCalle());

        if (clienteDTO.getDireccionNumero() != null)
            cliente.setDireccionNumero(clienteDTO.getDireccionNumero());

        if (clienteDTO.getDireccionPiso() != null)
            cliente.setDireccionPiso(clienteDTO.getDireccionPiso());

        if (clienteDTO.getDireccionDepto() != null)
            cliente.setDireccionDepto(clienteDTO.getDireccionDepto());

        clienteRepository.save(cliente);
        return new ResponseEntity<>("Cliente modificado", HttpStatus.OK);
    }

    public ResponseEntity<?> darDeBaja(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null)
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);

        cliente.setFechaBaja(LocalDateTime.now());
        cliente.setFechaModificacion(LocalDateTime.now());
        cliente.setEstaActivo(false);
        clienteRepository.save(cliente);

        return new ResponseEntity<>("Cliente dado de baja", HttpStatus.OK);
    }

}
