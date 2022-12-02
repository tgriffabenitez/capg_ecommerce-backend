package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.TipoPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonalizacionRepository extends JpaRepository<TipoPersonalizacion, Integer> {
    TipoPersonalizacion findByTipo(String tipo);
    boolean existsByTipo(String tipo);

}
