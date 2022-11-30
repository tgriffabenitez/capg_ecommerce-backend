package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "categorias")
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    boolean existsByCategoria(String categoriaIngresada);

    Categoria findByCategoria(String categoria);
}
