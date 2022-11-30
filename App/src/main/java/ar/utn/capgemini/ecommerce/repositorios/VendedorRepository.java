package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.personas.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "vendedores")
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    boolean existsByTienda(String nombreTienda);

    Vendedor findByTienda(String nombreTienda);
}
