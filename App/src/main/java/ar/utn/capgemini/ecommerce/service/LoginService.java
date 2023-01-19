package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.LoginDTO;
import ar.utn.capgemini.ecommerce.model.Cliente;
import ar.utn.capgemini.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class LoginService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> iniciarSesion(LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Cliente cliente = clienteRepository.findByEmailAndContrasenia(loginDTO.getEmail(), loginDTO.getContrasenia());
        if (cliente == null)
            return new ResponseEntity<>("Usuario o contrasenia incorrectos", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
