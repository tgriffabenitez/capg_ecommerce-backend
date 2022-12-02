package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.PosiblePersonalizacion;
import ar.utn.capgemini.ecommerce.model.entities.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PosiblePersonalizacionRepository extends JpaRepository<PosiblePersonalizacion, Integer> {
    PosiblePersonalizacion findByTipoPersonalizacionAndAreaPersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion);

    boolean existsByAreaPersonalizacionIdAndTipoPersonalizacionId(Integer areaId, Integer tipoId);
}
