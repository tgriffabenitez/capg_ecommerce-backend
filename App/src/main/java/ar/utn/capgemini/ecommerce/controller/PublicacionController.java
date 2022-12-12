package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.dto.PublicacionDTO;
import ar.utn.capgemini.ecommerce.model.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.model.Publicacion;
import ar.utn.capgemini.ecommerce.model.Vendedor;
import ar.utn.capgemini.ecommerce.repository.ProductoPersonalizadoRepository;
import ar.utn.capgemini.ecommerce.repository.PublicacionRepository;
import ar.utn.capgemini.ecommerce.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @Autowired
    private VendedorRepository vendedorRepository;

    @PersistenceContext
    private EntityManager em;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> obtenerPublicaciones(@RequestParam(name = "titulo", required = false) String titulo, @RequestParam(name = "descripcion", required = false) String descripcion, @RequestParam(name = "tienda", required = false) String tienda) {

        if (titulo != null && descripcion != null && tienda != null) {
            if (publicacionRepository.findByTituloAndDescripcion(titulo, descripcion).isEmpty()) {
                return new ResponseEntity<>("Titulo, descripcion y tienda no encontrados", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(publicacionRepository.findByTituloAndDescripcion(titulo, descripcion), HttpStatus.OK);

        } else if (titulo != null) {
            if (publicacionRepository.findByTitulo(titulo).isEmpty()) {
                return new ResponseEntity<>("Titulo no encontrado", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(publicacionRepository.findByTitulo(titulo), HttpStatus.OK);

        } else if (descripcion != null) {
            if (publicacionRepository.findByDescripcion(descripcion).isEmpty()) {
                return new ResponseEntity<>("Descripcion no encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(publicacionRepository.findByDescripcion(descripcion), HttpStatus.OK);

        } else if (tienda != null) {

            Vendedor vendedor = vendedorRepository.findByTienda(tienda);
            if (vendedor == null) {
                // si no encuentra la tienda el vendedor es null, por lo tanto no existe
                return new ResponseEntity<>("Tienda no encontrada", HttpStatus.NOT_FOUND);
            }

            // si encuentra la tienda, busco el id del vendedor
            Integer vendedorId = vendedor.getId();

            // busco las publicaciones del vendedor
            List<Publicacion> publicacionesVendedor = publicacionRepository.findByVendedorId(vendedorId);
            return new ResponseEntity<>(publicacionesVendedor, HttpStatus.OK);

        } else {
            Query query = em.createQuery("SELECT p FROM Publicacion p WHERE p.estaActivo = true");
            return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
        }
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
        publicacionActualizada.setProductoPersonalizado(productoPersonalizado);
        publicacionActualizada.setFechaCambioEstado(LocalDateTime.now());
        publicacionRepository.save(publicacionActualizada);

        return new ResponseEntity<>("Publicacion actualizada", HttpStatus.OK);
    }
} // fin PublicacionController
