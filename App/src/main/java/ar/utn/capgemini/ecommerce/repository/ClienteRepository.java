package ar.utn.capgemini.ecommerce.repository;

import ar.utn.capgemini.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByEmail(String email);

    Cliente findByEmailAndContrasenia(String email, String contrasenia);
}
