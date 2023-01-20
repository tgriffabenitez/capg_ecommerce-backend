package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.CompraDTO;
import ar.utn.capgemini.ecommerce.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping(path = {"/", ""})
    public ResponseEntity<?> crearCarrito(@RequestBody @Valid CompraDTO compraDTO, BindingResult bindingResult) {
        return carritoService.generarCompra(compraDTO, bindingResult);
    }
}
