package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.PublicacionDTO;
import ar.utn.capgemini.ecommerce.model.Categoria;
import ar.utn.capgemini.ecommerce.model.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.model.Publicacion;
import ar.utn.capgemini.ecommerce.model.Vendedor;
import ar.utn.capgemini.ecommerce.repository.CategoriaRepository;
import ar.utn.capgemini.ecommerce.repository.ProductoPersonalizadoRepository;
import ar.utn.capgemini.ecommerce.repository.PublicacionRepository;
import ar.utn.capgemini.ecommerce.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;


@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;

    public ResponseEntity<?> filtarPublicaciones(String buscarTitulo, String buscarTienda, String buscarCategoria) {

        if (buscarTitulo == null && buscarTienda == null && buscarCategoria == null) {
            return new ResponseEntity<>(publicacionRepository.findAll(), HttpStatus.OK);

        } else if (buscarTitulo == null && buscarTienda == null) {
            Categoria categoria = categoriaRepository.findByCategoria(buscarCategoria);
            if (categoria == null)
                return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(publicacionRepository.findByCategoriaId(categoria.getId()), HttpStatus.OK);

        } else if (buscarTitulo == null && buscarCategoria == null) {
            Vendedor vendedor = vendedorRepository.findByTienda(buscarTienda).orElse(null);
            if (vendedor == null)
                return new ResponseEntity<>("La tienda no pertenece a ningun vendedor", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(publicacionRepository.findByVendedorId(vendedor.getId()), HttpStatus.OK);

        } else if (buscarTitulo == null) {
            Vendedor vendedor = vendedorRepository.findByTienda(buscarTienda).orElse(null);
            if (vendedor == null)
                return new ResponseEntity<>("La tienda no pertenece a ningun vendedor", HttpStatus.NOT_FOUND);

            Categoria categoria = categoriaRepository.findByCategoria(buscarCategoria);
            if (categoria == null)
                return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(publicacionRepository.findByVendedorIdAndCategoriaId(vendedor.getId(), categoria.getId()), HttpStatus.OK);

        } else if (buscarTienda == null && buscarCategoria == null) {
            return new ResponseEntity<>(publicacionRepository.findByTitulo(buscarTitulo), HttpStatus.OK);

        } else if (buscarTienda == null) {
            Categoria categoria = categoriaRepository.findByCategoria(buscarCategoria);
            if (categoria == null)
                return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(publicacionRepository.findByTituloAndCategoriaId(buscarTitulo, categoria.getId()), HttpStatus.OK);

        } else if (buscarCategoria == null) {
            Vendedor vendedor = vendedorRepository.findByTienda(buscarTienda).orElse(null);
            if (vendedor == null)
                return new ResponseEntity<>("La tienda no pertenece a ningun vendedor", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(publicacionRepository.findByVendedorIdAndTitulo(vendedor.getId(), buscarTitulo), HttpStatus.OK);

        } else {
            Vendedor vendedor = vendedorRepository.findByTienda(buscarTienda).orElse(null);
            if (vendedor == null)
                return new ResponseEntity<>("La tienda no pertenece a ningun vendedor", HttpStatus.NOT_FOUND);

            Categoria categoria = categoriaRepository.findByCategoria(buscarCategoria);
            if (categoria == null)
                return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(publicacionRepository.findByVendedorIdAndCategoriaIdAndTitulo(vendedor.getId(), categoria.getId(), buscarTitulo), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> darPublicacionDeBaja(Integer id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElse(null);
        if (publicacion == null)
            return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.NOT_FOUND);

        publicacion.setFechaDeBaja(LocalDateTime.now());
        publicacion.setFechaCambioEstado(LocalDateTime.now());
        publicacion.setEstaActivo(false);
        publicacionRepository.save(publicacion);

        return new ResponseEntity<>("Publicacion dada de baja", HttpStatus.OK);
    }

    public ResponseEntity<?> modificarPublicacion(Integer id, PublicacionDTO publicacionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Publicacion publicacion = publicacionRepository.findById(id).orElse(null);
        if (publicacion == null)
            return new ResponseEntity<>("No se encontro la publicacion", HttpStatus.NOT_FOUND);

        if (publicacionDTO.getTitulo() != null)
            publicacion.setTitulo(publicacionDTO.getTitulo());

        if (publicacionDTO.getDescripcion() != null)
            publicacion.setDescripcion(publicacionDTO.getDescripcion());

        if (publicacionDTO.getProductoPersonalizadoId() != null){
            ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(publicacionDTO.getProductoPersonalizadoId()).orElse(null);
            if (productoPersonalizado == null)
                return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);
            publicacion.setProductoPersonalizado(productoPersonalizado);
        }

        if (publicacionDTO.getPrecio() != null)
            publicacion.setPrecio(publicacionDTO.getPrecio());

        publicacionRepository.save(publicacion);
        return new ResponseEntity<>("Publicacion actualizada", HttpStatus.OK);
    }
}
