package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.PublicacionDTO;
import ar.utn.capgemini.ecommerce.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping(path = {""})
    public ResponseEntity<?> filtarPublicaciones(@RequestParam(name = "titulo", required = false) String titulo,
                                                 @RequestParam(name = "tienda", required = false) String tienda,
                                                 @RequestParam(name = "categoria", required = false) String categoria) {

       return publicacionService.filtarPublicaciones(titulo, tienda, categoria);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> darPublicacionDeBaja(@PathVariable Integer id) {
        return publicacionService.darPublicacionDeBaja(id);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPublicacion(@PathVariable Integer id, @RequestBody @Valid PublicacionDTO publicacionDTO, BindingResult bindingResult) {
        return publicacionService.modificarPublicacion(id, publicacionDTO, bindingResult);
    }
} // fin PublicacionController
