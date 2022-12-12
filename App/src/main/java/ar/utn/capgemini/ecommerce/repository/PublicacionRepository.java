package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<?> findByTituloAndDescripcion(String titulo, String descripcion);

    List<?> findByTitulo(String titulo);

    List<?> findByDescripcion(String descripcion);

    List<Publicacion> findByVendedorId(Integer vendedorId);
}
