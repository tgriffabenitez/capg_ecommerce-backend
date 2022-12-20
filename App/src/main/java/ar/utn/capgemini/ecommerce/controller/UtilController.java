package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PosiblePersonalizacionRepository posiblePersonalizacion;
    @Autowired
    private ProductoBaseRepository productoBaseRepository;
    @Autowired
    private AreaPersonalizacionRepository areaPersonalizacionRepository;
    @Autowired
    private TipoPersonalizacionRepository tipoPersonalizacionRepository;
    @Autowired
    private VendedorRepository vendedorRepository;


    @GetMapping("/tipoPersonalizacion")
    public ResponseEntity<?> getTipoPersonalizacion() {
        return ResponseEntity.ok(tipoPersonalizacionRepository.findAll());
    }

    @GetMapping("/areaPersonalizacion")
    public ResponseEntity<?> getAreaPersonalizacion() {
        return ResponseEntity.ok(areaPersonalizacionRepository.findAll());
    }

    @GetMapping("/posiblePersonalizacion")
    public ResponseEntity<?> getPosiblePersonalizacion() {
        return ResponseEntity.ok(posiblePersonalizacion.findAll());
    }

    @GetMapping("/productoBase")
    public ResponseEntity<?> getProductoBase() {
        return ResponseEntity.ok(productoBaseRepository.findAll());
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> getCategoria() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/vendedores")
    public ResponseEntity<?> getVendedores() {
        return ResponseEntity.ok(vendedorRepository.findAll());
    }
}
