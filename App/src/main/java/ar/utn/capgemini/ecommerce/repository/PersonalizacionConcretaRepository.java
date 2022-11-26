package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.PersonalizacionConcreta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController(path = "personalizacionConcreta")
public interface PersonalizacionConcretaRepository extends JpaRepository<PersonalizacionConcreta, Integer> {
}
