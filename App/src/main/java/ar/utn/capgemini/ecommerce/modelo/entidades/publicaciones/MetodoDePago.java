package ar.utn.capgemini.ecommerce.modelo.entidades.publicaciones;

import ar.utn.capgemini.ecommerce.modelo.enums.PAGO;
import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "metodoDePago")
public class MetodoDePago extends EntidadPersistente {

    @NotNull
    @Enumerated(EnumType.STRING)
    private PAGO formaDePago;

    public MetodoDePago(PAGO formaDePago) {
        this.formaDePago = formaDePago;
    }

    public MetodoDePago() {

    }

}
