package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

}
