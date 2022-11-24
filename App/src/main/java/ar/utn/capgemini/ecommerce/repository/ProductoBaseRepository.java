package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "productos")
public interface ProductoBaseRepository extends JpaRepository<ProductoBase, Integer> {

}