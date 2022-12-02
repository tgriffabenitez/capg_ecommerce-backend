package ar.utn.capgemini.ecommerce.repositorios;

import ar.utn.capgemini.ecommerce.modelo.entidades.publicaciones.MetodoDePago;
import ar.utn.capgemini.ecommerce.modelo.enums.PAGO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Integer> {
    MetodoDePago findByFormaDePago(PAGO formaDePago);

    boolean existsByFormaDePago(PAGO formaDePago);
}
