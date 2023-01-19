package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    Optional<Vendedor> findByTienda(String nombreTienda);

    boolean existsByTienda(String tiendaVendedor);

}