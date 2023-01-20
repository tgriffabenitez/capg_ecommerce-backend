package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.PersonalizacionConcretaDTO;
import ar.utn.capgemini.ecommerce.dto.ProductoPersonalizadoDTO;
import ar.utn.capgemini.ecommerce.service.ProductoPersonalizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productoPersonalizado")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoService productoPersonalizadoService;

    @GetMapping(path = "")
    public ResponseEntity<?> obtenerProductosPersonalizados() {
        return productoPersonalizadoService.listarProductosPersonalizados();
    }

    @GetMapping(path = "/{productoPersonalizadoId}")
    public ResponseEntity<?> obtenerProductoPersonalizadoId(@PathVariable Integer productoPersonalizadoId) {
        return productoPersonalizadoService.obtenerProductoPersonalizado(productoPersonalizadoId);
    }

    @GetMapping(path = "/{productoPersonalizadoId}/personalizacionConcreta")
    public ResponseEntity<?> obtenerPersonalizacionConcreta(@PathVariable Integer productoPersonalizadoId) {
        return productoPersonalizadoService.obtenerPersonalizacionesConcretas(productoPersonalizadoId);
    }

    @GetMapping(path = "/{productoPersonalizadoId}/productoBase")
    public ResponseEntity<?> obtenerProductoBase(@PathVariable Integer productoPersonalizadoId) {
        return productoPersonalizadoService.obtenerProductoBase(productoPersonalizadoId);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> crearProductoPersonalizado(@RequestBody ProductoPersonalizadoDTO productoPersonalizadoDTO, BindingResult bindingResult) {
        return productoPersonalizadoService.crearProductoPersonalizado(productoPersonalizadoDTO, bindingResult);
    }

    @PostMapping(path = "/{productoPersonalizadoId}/personalizacionConcreta")
    public ResponseEntity<?> agregarPersonalizacionConcreta(@PathVariable Integer productoPersonalizadoId, @RequestBody PersonalizacionConcretaDTO personalizacionConcretaDTO, BindingResult bindingResult) {
        return productoPersonalizadoService.agregarPersonalizacionConcreta(productoPersonalizadoId, personalizacionConcretaDTO, bindingResult);
    }

    @DeleteMapping(path = "/{productoPersonalizadoId}")
    public ResponseEntity<?> darProductoPersonalizadoDeBaja(@PathVariable Integer productoPersonalizadoId) {
        return productoPersonalizadoService.darProductoPersonalizadoDeBaja(productoPersonalizadoId);
    }

} // fin ProductoPersonalizadoController