package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.AreaPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaPersonalizacionRepository extends JpaRepository<AreaPersonalizacion, Integer> {
    AreaPersonalizacion findByArea(String area);
    boolean existsByArea(String area);
}
