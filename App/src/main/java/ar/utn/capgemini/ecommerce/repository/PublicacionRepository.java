package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findByTitulo(String titulo);
    List<Publicacion> findByVendedorId(Integer vendedorId);
    List<Publicacion> findByCategoriaId(Integer categoriaId);
    List<Publicacion> findByVendedorIdAndCategoriaId(Integer id, Integer id1);
    List<Publicacion> findByTituloAndCategoriaId(String buscarTitulo, Integer id);
    List<Publicacion> findByVendedorIdAndTitulo(Integer id, String buscarTitulo);
    List<Publicacion> findByVendedorIdAndCategoriaIdAndTitulo(Integer id, Integer id1, String buscarTitulo);
}