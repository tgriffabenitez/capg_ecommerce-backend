package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.PersonalizacionConcreta;
import ar.utn.capgemini.ecommerce.model.entities.PosiblePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacionConcretaRepository extends JpaRepository<PersonalizacionConcreta, Integer> {
    boolean existsByPosiblePersonalizacionIdAndDetalle(Integer posibleId, String detalle);

    PersonalizacionConcreta findByPosiblePersonalizacionAndDetalle(PosiblePersonalizacion posiblePersonalizacion, String detalle);
}
