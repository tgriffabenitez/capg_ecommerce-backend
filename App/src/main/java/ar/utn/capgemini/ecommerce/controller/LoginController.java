package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.LoginDTO;
import ar.utn.capgemini.ecommerce.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity<?> iniciarSesion(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult){
        return loginService.iniciarSesion(loginDTO, bindingResult);
    }
}
