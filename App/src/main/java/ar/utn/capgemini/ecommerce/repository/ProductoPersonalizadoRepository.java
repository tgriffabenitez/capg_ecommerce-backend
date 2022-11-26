package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.entities.ProductoPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "productoPersonalizaco")
public interface ProductoPersonalizadoRepository extends JpaRepository<ProductoPersonalizado, Integer> {
}
