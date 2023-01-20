package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.PosiblesPersonalizacionesDTO;
import ar.utn.capgemini.ecommerce.dto.ProductoBaseDTO;
import ar.utn.capgemini.ecommerce.service.ProductoBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/productoBase")
public class ProductoBaseController {


    @Autowired
    private ProductoBaseService productoBaseService;

    @GetMapping(path = "")
    public ResponseEntity<?> listarProductosBase() {
        return productoBaseService.listarProductosBase();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoBase(@PathVariable("id") Integer id) {
        return productoBaseService.obtenerProductoBase(id);
    }

    @PostMapping("")
    public ResponseEntity<?> crearProductoBase(@RequestBody ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        return productoBaseService.crearProductoBase(productoBaseDTO, bindingResult);
    }

    @PostMapping("/{id}/posiblePersonalizacion")
    public ResponseEntity<?> agregarPosiblePersonalizacion(@PathVariable("id") Integer id, @RequestBody PosiblesPersonalizacionesDTO posiblesPersonalizacionesDTO, BindingResult bindingResult) {
        return productoBaseService.agregarPosiblePersonalizacion(id, posiblesPersonalizacionesDTO, bindingResult);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> modificarProductoBase(@PathVariable Integer id, @RequestBody ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        return productoBaseService.modificarProductoBase(id, productoBaseDTO, bindingResult);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> darProductoBaseDeBaja(@PathVariable Integer id) {
        return productoBaseService.darDeBaja(id);
    }

} // fin ProductoBaseController
