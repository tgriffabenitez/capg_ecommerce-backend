package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import ar.utn.capgemini.ecommerce.model.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {

    @NotNull
    @Past
    @Column(name = "fechaDeCompra", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaDeCompra;

    @NotNull
    @Past
    @Column(name = "horaDeCompra", columnDefinition = "TIMESTAMP")
    private LocalDateTime horaDeCompra;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PAGO metodoDePago;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADO estadoDeCompra;

    @NotNull
    @Past
    @Column(name = "fechaCambioEstado", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCambioEstado;

    @NotNull
    @Positive
    @Column(name = "precioTotal")
    private double precioTotal;

    @OneToMany
    @JoinColumn(name = "publicacionCarrito", referencedColumnName = "id")
    private List<PublicacionCarrito> publicacionesCarrito;

    public Compra(LocalDateTime fechaDeCompra, LocalDateTime horaDeCompra, PAGO metodoDePago, ESTADO estadoDeCompra, LocalDateTime fechaCambioEstado, double precioTotal) {
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
