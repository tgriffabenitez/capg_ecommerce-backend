package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.AreaPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "areasPersonalizacion")
public interface AreaPersonalizacionRepository extends JpaRepository<AreaPersonalizacion, Integer> {
    AreaPersonalizacion findByArea(String area);
    boolean existsByArea(String area);
}
