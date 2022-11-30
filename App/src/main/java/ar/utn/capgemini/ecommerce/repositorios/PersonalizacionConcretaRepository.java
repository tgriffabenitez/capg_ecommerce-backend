package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PersonalizacionConcreta;
import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PosiblePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController(path = "personalizacionConcreta")
public interface PersonalizacionConcretaRepository extends JpaRepository<PersonalizacionConcreta, Integer> {
    boolean existsByPosiblePersonalizacionIdAndDetalle(Integer posibleId, String detalle);

    PersonalizacionConcreta findByPosiblePersonalizacionAndDetalle(PosiblePersonalizacion posiblePersonalizacion, String detalle);
}
