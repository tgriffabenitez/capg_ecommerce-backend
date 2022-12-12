package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    boolean existsByTienda(String nombreTienda);

    Vendedor findByTienda(String nombreTienda);
}
