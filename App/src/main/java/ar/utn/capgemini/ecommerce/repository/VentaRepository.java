package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
