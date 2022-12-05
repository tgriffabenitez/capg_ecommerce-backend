package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.PublicacionPorCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionCarritoRepository extends JpaRepository<PublicacionPorCarrito, Integer> {
}
