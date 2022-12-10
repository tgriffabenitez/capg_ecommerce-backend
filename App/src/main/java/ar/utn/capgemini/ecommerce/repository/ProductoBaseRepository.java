package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.ProductoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoBaseRepository extends JpaRepository<ProductoBase, Integer> {
}
