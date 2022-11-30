package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PersonalizacionConcreta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController(path = "personalizacionConcreta")
public interface PersonalizacionConcretaRepository extends JpaRepository<PersonalizacionConcreta, Integer> {
}
