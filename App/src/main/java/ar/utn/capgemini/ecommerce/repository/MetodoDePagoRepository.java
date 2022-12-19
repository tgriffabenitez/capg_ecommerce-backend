package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Integer> {

}
