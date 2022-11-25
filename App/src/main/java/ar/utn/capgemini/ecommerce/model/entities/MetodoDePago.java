package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.enums.PAGO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "metodoDePago")
public class MetodoDePago extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    private PAGO formaDePago;

    public MetodoDePago() {

    }

}
