package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonalizacionRepository extends JpaRepository<TipoPersonalizacion, Integer> {
    TipoPersonalizacion findByTipo(String tipo);
    boolean existsByTipo(String tipo);

}
