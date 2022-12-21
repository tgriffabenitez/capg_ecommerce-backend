package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.PersonalizacionConcretaDTO;
import ar.utn.capgemini.ecommerce.dto.ProductoPersonalizadoDTO;
import ar.utn.capgemini.ecommerce.model.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/productoPersonalizado")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;
    @Autowired
    private ProductoBaseRepository productoBaseRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private PersonalizacionConcretaRepository personalizacionConcretaRepository;
    @Autowired
    private PosiblePersonalizacionRepository posiblePersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> obtenerProductosPersonalizados() {
        return new ResponseEntity<>(productoPersonalizadoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> obtenerProductoPersonalizadoId(@PathVariable Integer id) {
        if (!productoPersonalizadoRepository.findById(id).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productoPersonalizadoRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<?> crearProductoPersonalizado(@RequestBody ProductoPersonalizadoDTO personalizadoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!productoBaseRepository.findById(personalizadoDTO.getProductoBaseId()).isPresent())
            return new ResponseEntity<>("No se encontro el producto base", HttpStatus.NOT_FOUND);

        if (!vendedorRepository.findById(personalizadoDTO.getVendedorId()).isPresent())
            return new ResponseEntity<>("No se encontro el vendedor", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(personalizadoDTO.getProductoBaseId()).get();
        Vendedor vendedor = vendedorRepository.findById(personalizadoDTO.getVendedorId()).get();
        String productoPersonalizadoUrl = personalizadoDTO.getProductoPersonalizadoUrl();

        ProductoPersonalizado productoPersonalizado = new ProductoPersonalizado();
        productoPersonalizado.setProductoPersonalizadoUrl(productoPersonalizadoUrl);
        productoPersonalizado.setProductoBase(productoBase);
        productoPersonalizado.setVendedor(vendedor);
        productoPersonalizado.setFechaDeAlta(LocalDateTime.now());
        productoPersonalizadoRepository.save(productoPersonalizado);

        return new ResponseEntity<>("Se agrego el producto personalizaco con id: " + productoPersonalizado.getId(), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> modificarProductoPersonalizado(@PathVariable Integer id, @RequestBody @Valid ProductoPersonalizadoDTO personalizadoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!productoPersonalizadoRepository.findById(id).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        if (!productoBaseRepository.findById(personalizadoDTO.getProductoBaseId()).isPresent())
            return new ResponseEntity<>("No se encontro el producto base", HttpStatus.NOT_FOUND);

        if (!vendedorRepository.findById(personalizadoDTO.getVendedorId()).isPresent())
            return new ResponseEntity<>("No se encontro el vendedor", HttpStatus.NOT_FOUND);

        String productoPersonalizadoUrl = personalizadoDTO.getProductoPersonalizadoUrl();
        ProductoBase productoBase = productoBaseRepository.findById(personalizadoDTO.getProductoBaseId()).get();
        Vendedor vendedor = vendedorRepository.findById(personalizadoDTO.getVendedorId()).get();

        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
        productoPersonalizado.setProductoBase(productoBase);
        productoPersonalizado.setProductoPersonalizadoUrl(productoPersonalizadoUrl);
        productoPersonalizado.setVendedor(vendedor);
        productoPersonalizado.setFechaUltimaModificacion(LocalDateTime.now());
        productoPersonalizadoRepository.save(productoPersonalizado);

        return new ResponseEntity<>("Se modifico el producto personalizado con id: " + productoPersonalizado.getId(), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> darProductoPersonalizadoDeBaja(@PathVariable Integer id) {
        if (!productoPersonalizadoRepository.findById(id).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
        productoPersonalizado.setEstaActivo(false);
        productoPersonalizado.setFechaDeBaja(LocalDateTime.now());
        productoPersonalizado.setFechaUltimaModificacion(LocalDateTime.now());

        return new ResponseEntity<>("Se dio de baja el producto personalizado con id: " + productoPersonalizado.getId(), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/personalizacionConcreta")
    public ResponseEntity<?> agregarPersonalizacionConcreta(@PathVariable Integer id, @RequestBody PersonalizacionConcretaDTO personalizacionConcretaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!productoPersonalizadoRepository.findById(id).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        if (!posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).isPresent())
            return new ResponseEntity<>("No se encontro la posible personalizacion", HttpStatus.NOT_FOUND);

        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
        PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).get();

        PersonalizacionConcreta personalizacionConcreta = new PersonalizacionConcreta();
        personalizacionConcreta.setDetalle(personalizacionConcretaDTO.getDetalle());
        personalizacionConcreta.setPrecioPersonalizacionConcreta(personalizacionConcretaDTO.getPrecioPersonalizacion());
        personalizacionConcreta.setPosiblePersonalizacion(posiblePersonalizacion);
        personalizacionConcretaRepository.save(personalizacionConcreta);

        productoPersonalizado.agregarPersonalizacionConcreta(personalizacionConcreta);
        productoPersonalizadoRepository.save(productoPersonalizado);

        return new ResponseEntity<>("Se agrego la personalizacion concreta con id: " + personalizacionConcreta.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/personalizacionConcreta")
    public ResponseEntity<?> obtenerPersonalizacionConcreta(@PathVariable Integer id) {
        if (!productoPersonalizadoRepository.findById(id).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
        List<PersonalizacionConcreta> personalizacionesConcretas = productoPersonalizado.getPersonalizacionesConcretas();

        return new ResponseEntity<>(personalizacionesConcretas, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/{productoPersonalizadoId}/personalizacionConcreta/{personalizacionConcretaId}")
    public ResponseEntity<?> darPersonalizacionConcretaDeBaja(@PathVariable Integer productoPersonalizadoId, @PathVariable Integer personalizacionConcretaId) {
        if (!productoPersonalizadoRepository.findById(productoPersonalizadoId).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        if (!personalizacionConcretaRepository.findById(personalizacionConcretaId).isPresent())
            return new ResponseEntity<>("No se encontro la personalizacion concreta", HttpStatus.NOT_FOUND);

        PersonalizacionConcreta personalizacionConcreta = personalizacionConcretaRepository.findById(personalizacionConcretaId).get();
        personalizacionConcreta.setEstaActivo(false);
        personalizacionConcreta.setFechaDeBaja(LocalDateTime.now());
        personalizacionConcreta.setFechaUltimaModificacion(LocalDateTime.now());

        return new ResponseEntity<>("Se modifico la personalizacion concreta con exito", HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(path = "/{productoPersonalizadoId}/personalizacionConcreta/{personalizacionConcretaId}")
    public ResponseEntity<?> modificarPersonalizacionConcretaId(@PathVariable Integer productoPersonalizadoId, @PathVariable Integer personalizacionConcretaId, @RequestBody @Valid PersonalizacionConcretaDTO personalizacionConcretaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!productoPersonalizadoRepository.findById(productoPersonalizadoId).isPresent())
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        if (!personalizacionConcretaRepository.findById(personalizacionConcretaId).isPresent())
            return new ResponseEntity<>("No se encontro la personalizacion concreta", HttpStatus.NOT_FOUND);

        if (!posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).isPresent())
            return new ResponseEntity<>("No se encontro la posible personalizacion", HttpStatus.NOT_FOUND);

        String detalleNuevo = personalizacionConcretaDTO.getDetalle();
        Double precioNuevo = personalizacionConcretaDTO.getPrecioPersonalizacion();

        PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).get();
        PersonalizacionConcreta personalizacionConcreta = personalizacionConcretaRepository.findById(personalizacionConcretaId).get();
        personalizacionConcreta.setDetalle(detalleNuevo);
        personalizacionConcreta.setPrecioPersonalizacionConcreta(precioNuevo);
        personalizacionConcreta.setPosiblePersonalizacion(posiblePersonalizacion);
        personalizacionConcreta.setFechaUltimaModificacion(LocalDateTime.now());

        return new ResponseEntity<>("Se actualizaco correctamente la personalizacion concreta", HttpStatus.OK);
    }
} // fin ProductoPersonalizadoController