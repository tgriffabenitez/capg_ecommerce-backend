package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tiposPersonalizacion")
public interface TipoPersonalizacionRepository extends JpaRepository<TipoPersonalizacion, Integer> {
    TipoPersonalizacion findByDescripcion(String tipo);
    boolean existsByDescripcion(String tipo);

}
