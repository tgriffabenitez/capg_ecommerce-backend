package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "metodosDePago")
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Integer> {
}
