package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "factura")
public class Factura extends EntidadPersistente {

    private LocalDate fechaDeFactura;

    private LocalTime horaDeFactura;

    private Double precioFinal;

    private Compra compra;

    public Factura() {
    }
}
