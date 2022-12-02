package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.AreaPersonalizacion;
import ar.utn.capgemini.ecommerce.modelo.entidades.productos.PosiblePersonalizacion;
import ar.utn.capgemini.ecommerce.modelo.entidades.productos.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PosiblePersonalizacionRepository extends JpaRepository<PosiblePersonalizacion, Integer> {
    PosiblePersonalizacion findByTipoPersonalizacionAndAreaPersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion);

    boolean existsByAreaPersonalizacionIdAndTipoPersonalizacionId(Integer areaId, Integer tipoId);
}
