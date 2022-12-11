package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.PublicacionDTO;
import ar.utn.capgemini.ecommerce.model.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.model.Publicacion;
import ar.utn.capgemini.ecommerce.repository.ProductoPersonalizadoRepository;
import ar.utn.capgemini.ecommerce.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;

    @PersistenceContext
    private EntityManager em;

    @GetMapping(path = {"", "/"})
    public List<?> obtenerPublicaciones() {
        return em.createQuery("SELECT p FROM Publicacion p WHERE p.estaActivo = true").getResultList();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> darPublicacionDeBaja(@PathVariable Integer id) {
        if (!publicacionRepository.findById(id).isPresent())
            return new ResponseEntity<>("No existe la publicacion", HttpStatus.NOT_FOUND);

        Publicacion publicacion = publicacionRepository.findById(id).get();
        publicacion.setEstaActivo(false);
        publicacion.setFechaDeBaja(LocalDateTime.now());
        publicacion.setFechaCambioEstado(LocalDateTime.now());
        publicacionRepository.save(publicacion);

        return new ResponseEntity<>("Publicacion dada de baja", HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPublicacion(@PathVariable Integer id, @RequestBody @Valid PublicacionDTO publicacion) {
        if (!publicacionRepository.findById(id).isPresent())
            return new ResponseEntity<>("No existe la publicacion", HttpStatus.NOT_FOUND);

        if (!productoPersonalizadoRepository.findById(publicacion.getProductoPersonalizadoId()).isPresent())
            return new ResponseEntity<>("No existe el producto personalizado", HttpStatus.NOT_FOUND);

        Publicacion publicacionActualizada = publicacionRepository.findById(id).get();
        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(publicacion.getProductoPersonalizadoId()).get();

        publicacionActualizada.setTitulo(publicacion.getTitulo());
        publicacionActualizada.setDescripcion(publicacion.getDescripcion());
        publicacionActualizada.setPreciototal(publicacion.getPrecio());
        publicacionActualizada.setProductoPersonalizado(productoPersonalizado);
        publicacionActualizada.setFechaCambioEstado(LocalDateTime.now());
        publicacionRepository.save(publicacionActualizada);

        return new ResponseEntity<>("Publicacion actualizada", HttpStatus.OK);
    }
} // fin PublicacionController
