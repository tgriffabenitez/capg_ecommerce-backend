package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.ClienteDTO;
import ar.utn.capgemini.ecommerce.model.Cliente;
import ar.utn.capgemini.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }

    public Optional<?> findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public void agregarCliente(ClienteDTO clienteDTO, String email) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(email);
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setContrasenia(clienteDTO.getContrasenia());
        cliente.setDireccionCalle(clienteDTO.getDireccionCalle());
        cliente.setDireccionNumero(clienteDTO.getDireccionNumero());
        cliente.setDireccionPiso(clienteDTO.getDireccionPiso());
        cliente.setDireccionDepto(clienteDTO.getDireccionDepto());
        clienteRepository.save(cliente);
    }
}
