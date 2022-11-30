package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tiposPersonalizacion")
public interface TipoPersonalizacionRepository extends JpaRepository<TipoPersonalizacion, Integer> {
    TipoPersonalizacion findByTipo(String tipo);
    boolean existsByTipo(String tipo);

}
