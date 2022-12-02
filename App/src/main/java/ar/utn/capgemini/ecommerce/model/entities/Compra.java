package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import ar.utn.capgemini.ecommerce.model.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {

    @NotNull
    @Past
    @Column(name = "fechaDeCompra", columnDefinition = "DATE")
    private LocalDate fechaDeCompra;

    @NotNull
    @Past
    @Column(name = "horaDeCompra", columnDefinition = "TIME")
    private LocalTime horaDeCompra;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PAGO metodoDePago;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADO estadoDeCompra;

    @NotNull
    @Past
    @Column(name = "fechaCambioEstado", columnDefinition = "DATE")
    private LocalDate fechaCambioEstado;

    @NotNull
    @Positive
    @Column(name = "precioTotal")
    private double precioTotal;

    @OneToMany
    @JoinColumn(name = "publicacionCarrito", referencedColumnName = "id")
    private List<PublicacionCarrito> publicacionesCarrito;

    public Compra(LocalDate fechaDeCompra, LocalTime horaDeCompra, PAGO metodoDePago, ESTADO estadoDeCompra, LocalDate fechaCambioEstado, double precioTotal) {
        this.fechaDeCompra = fechaDeCompra;
        this.horaDeCompra = horaDeCompra;
        this.metodoDePago = metodoDePago;
        this.estadoDeCompra = estadoDeCompra;
        this.fechaCambioEstado = fechaCambioEstado;
        this.precioTotal = precioTotal;
    }

    public Compra() {
        this.publicacionesCarrito = new ArrayList<>();
    }

}
