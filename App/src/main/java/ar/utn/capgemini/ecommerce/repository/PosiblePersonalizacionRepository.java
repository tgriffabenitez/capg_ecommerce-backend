package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.PosiblePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PosiblePersonalizacionRepository extends JpaRepository<PosiblePersonalizacion, Integer> {

}
