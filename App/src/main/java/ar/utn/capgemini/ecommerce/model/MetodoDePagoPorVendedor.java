package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.enums.PAGO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "metodoDePagoPorVendedor")
public class MetodoDePagoPorVendedor extends EntidadPersistente {

    private Vendedor vendedor;

    private PAGO metodoDePago;

    public MetodoDePagoPorVendedor() {
    }
}
