package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "factura")
public class Factura extends EntidadPersistente {

    @Column(name = "fechaDeFactura", columnDefinition = "DATE")
    private LocalDate fechaDeFactura;

    @Column(name = "horaDeFactura", columnDefinition = "TIME")
    private LocalTime horaDeFactura;

    @Column(name = "precioFinal")
    private Double precioFinal;

    @OneToOne
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private Compra compra;

    public Factura() {
    }
}
