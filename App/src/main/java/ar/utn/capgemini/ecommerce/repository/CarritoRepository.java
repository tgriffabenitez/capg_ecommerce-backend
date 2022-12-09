package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
