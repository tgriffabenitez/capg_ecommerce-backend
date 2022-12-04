package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.PersonalizacionConcretaDTO;
import ar.utn.capgemini.ecommerce.model.dto.ProductoPersonalizadoDTO;
import ar.utn.capgemini.ecommerce.model.entities.*;
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
    public List<ProductoPersonalizado> obtenerProductosPersonalizados() {
        return productoPersonalizadoRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ProductoPersonalizado> obtenerProductoPersonalizadoId(@PathVariable Integer id) {
        if (productoPersonalizadoRepository.existsById(id)) {

            return new ResponseEntity<>(productoPersonalizadoRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<String> agregarProductoPersonalizado(@RequestBody ProductoPersonalizadoDTO personalizadoDTO, BindingResult bindingResult) {
        boolean existeProductoBase = productoBaseRepository.existsById(personalizadoDTO.getProductoBaseId());
        boolean existeVendedor = vendedorRepository.existsById(personalizadoDTO.getVendedorId());

        if (!bindingResult.hasErrors() && existeVendedor && existeProductoBase) {
            String productoPersonalizadoUrl = personalizadoDTO.getProductoPersonalizadoUrl();
            ProductoBase productoBase = productoBaseRepository.findById(personalizadoDTO.getProductoBaseId()).get();
            Vendedor vendedor = vendedorRepository.findById(personalizadoDTO.getVendedorId()).get();

            ProductoPersonalizado productoPersonalizado = new ProductoPersonalizado(productoPersonalizadoUrl, productoBase, vendedor, LocalDateTime.now());
            productoPersonalizadoRepository.save(productoPersonalizado);

            return new ResponseEntity<>("Producto personalizado agregado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al crear el productoPersonalizado", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> modificarProductoPersonalizado(@PathVariable Integer id, @RequestBody @Valid ProductoPersonalizadoDTO personalizadoDTO, BindingResult bindingResult) {
        boolean existeProductoPersonalizado = productoPersonalizadoRepository.existsById(id);

        if (!bindingResult.hasErrors() && existeProductoPersonalizado) {
            String productoPersonalizadoUrl = personalizadoDTO.getProductoPersonalizadoUrl();
            ProductoBase productoBase = productoBaseRepository.findById(personalizadoDTO.getProductoBaseId()).get();
            Vendedor vendedor = vendedorRepository.findById(personalizadoDTO.getVendedorId()).get();

            ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
            productoPersonalizado.setProductoBase(productoBase);
            productoPersonalizado.setProductoPersonalizadoUrl(productoPersonalizadoUrl);
            productoPersonalizado.setVendedor(vendedor);
            productoPersonalizado.setFechaUltimaModificacion(LocalDateTime.now());
            productoPersonalizadoRepository.save(productoPersonalizado);

            return new ResponseEntity<>("Producto personalizado modificado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);
        }
    }


    @Transactional
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> darProductoPersonalizadoDeBaja(@PathVariable Integer id) {
        boolean existeProductoPersonalizado = productoPersonalizadoRepository.existsById(id);

        if (existeProductoPersonalizado) {
            ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
            productoPersonalizado.setEstaActivo(false);
            productoPersonalizado.setFechaDeBaja(LocalDateTime.now());
            productoPersonalizado.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>("Producto personalizado dado de baja con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se enconctro el producto personalizado", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(path = "/{id}/personalizacionConcreta")
    public ResponseEntity<String> agregarPersonalizacionConcreta(@PathVariable Integer id, @RequestBody PersonalizacionConcretaDTO personalizacionConcretaDTO, BindingResult bindingResult) {
        boolean existeProductoPersonalizado = productoPersonalizadoRepository.existsById(id);
        boolean existePosiblePersonalizacion = posiblePersonalizacionRepository.existsById(personalizacionConcretaDTO.getPosiblePersonalizacionId());

        if (!bindingResult.hasErrors() && existeProductoPersonalizado && existePosiblePersonalizacion) {
            ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
            PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).get();

            PersonalizacionConcreta personalizacionConcreta = new PersonalizacionConcreta(personalizacionConcretaDTO.getDetalle(), personalizacionConcretaDTO.getPrecioPersonalizacionConcreta(), posiblePersonalizacion, LocalDateTime.now());
            personalizacionConcretaRepository.save(personalizacionConcreta);

            productoPersonalizado.agregarPersonalizacionConcreta(personalizacionConcreta);
            productoPersonalizadoRepository.save(productoPersonalizado);

            return new ResponseEntity<>("Se agego la personalizacionConcreta con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el productoPersonalizado o la posiblePersonalizacion", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/{id}/personalizacionConcreta")
    public ResponseEntity<List<PersonalizacionConcreta>> obtenerPersonalizacionConcreta(@PathVariable Integer id) {
        boolean existeProductoPersonalizado = productoPersonalizadoRepository.existsById(id);

        if (existeProductoPersonalizado) {
            ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).get();
            List<PersonalizacionConcreta> personalizacionesConcretas = productoPersonalizado.getPersonalizacionesConcretas();
            return new ResponseEntity<>(personalizacionesConcretas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = "/{productoPersonalizadoId}/personalizacionConcreta/{personalizacionConcretaId}")
    public ResponseEntity<String> darPersonalizacionConcretaDeBaja(@PathVariable Integer productoPersonalizadoId, @PathVariable Integer personalizacionConcretaId) {
        boolean existeProductoPersonalizado = productoPersonalizadoRepository.existsById(productoPersonalizadoId);
        boolean existePersonalizacionConcreta = personalizacionConcretaRepository.existsById(personalizacionConcretaId);

        if (existePersonalizacionConcreta && existeProductoPersonalizado) {
            PersonalizacionConcreta personalizacionConcreta = personalizacionConcretaRepository.findById(personalizacionConcretaId).get();
            personalizacionConcreta.setEstaActivo(false);
            personalizacionConcreta.setFechaDeBaja(LocalDateTime.now());

            return new ResponseEntity<>("Se modifico la personalizacion concreta con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el producto personalizado o la personalizacion concreta", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PatchMapping(path = "/{productoPersonalizadoId}/personalizacionConcreta/{personalizacionConcretaId}")
    public ResponseEntity<String> modificarPersonalizacionConcretaId(@PathVariable Integer productoPersonalizadoId, @PathVariable Integer personalizacionConcretaId, @RequestBody @Valid PersonalizacionConcretaDTO personalizacionConcretaDTO, BindingResult bindingResult) {
        boolean existeProductoPersonalizado = productoPersonalizadoRepository.existsById(productoPersonalizadoId);
        boolean existePersonalizacionConcreta = personalizacionConcretaRepository.existsById(personalizacionConcretaId);
        boolean existePosiblePersonalizacion = posiblePersonalizacionRepository.existsById(personalizacionConcretaDTO.getPosiblePersonalizacionId());

        if (!bindingResult.hasErrors() && existeProductoPersonalizado && existePersonalizacionConcreta) {
            String detalleNuevo = personalizacionConcretaDTO.getDetalle();
            Double precioNuevo = personalizacionConcretaDTO.getPrecioPersonalizacionConcreta();

            if (existePosiblePersonalizacion) {
                PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).get();
                PersonalizacionConcreta personalizacionConcreta = personalizacionConcretaRepository.findById(personalizacionConcretaId).get();
                personalizacionConcreta.setDetalle(detalleNuevo);
                personalizacionConcreta.setPrecioPersonalizacionConcreta(precioNuevo);
                personalizacionConcreta.setPosiblePersonalizacion(posiblePersonalizacion);
                personalizacionConcreta.setFechaUltimaModificacion(LocalDateTime.now());
                return new ResponseEntity<>("Se actualizaco correctamente la personalizacion concreta", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontro la personalizacion concreta", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("No se encontro el productoPersonalizado o la personalizacionConcreta", HttpStatus.NOT_FOUND);
        }


    }
}