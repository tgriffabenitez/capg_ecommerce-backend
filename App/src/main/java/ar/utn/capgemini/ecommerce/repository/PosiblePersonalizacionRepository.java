package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.PosiblePersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "posiblesPersonalizaciones")
public interface PosiblePersonalizacionRepository extends JpaRepository<PosiblePersonalizacion, Integer> {

    PosiblePersonalizacion findByTipoPersonalizacionAndAreaPersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion);

}
