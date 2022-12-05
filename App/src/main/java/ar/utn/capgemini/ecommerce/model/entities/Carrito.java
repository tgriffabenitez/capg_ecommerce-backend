package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import ar.utn.capgemini.ecommerce.model.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carrito")
public class Carrito extends EntidadPersistente {

    @NotNull
    @Past
    @Column(name = "fechaDeCompra", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeCompra;


    @NotNull
    @Enumerated(EnumType.STRING)
    private PAGO metodoDePago;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADO estadoDeCompra;

    @NotNull
    @Past
    @Column(name = "fechaCambioEstado", columnDefinition = "DATETIME")
    private LocalDateTime fechaCambioEstado;

    @NotNull
    @Positive
    @Column(name = "precioTotal")
    private BigDecimal precioTotal;

    @OneToMany
    @JoinColumn(name = "publicacionCarrito", referencedColumnName = "id")
    private List<PublicacionCarrito> publicacionesCarrito;

    public Carrito(LocalDateTime fechaDeCompra, PAGO metodoDePago, ESTADO estadoDeCompra, LocalDateTime fechaCambioEstado, BigDecimal precioTotal) {
        this.publicacionesCarrito = new ArrayList<>();
        this.fechaDeCompra = fechaDeCompra;
        this.metodoDePago = metodoDePago;
        this.estadoDeCompra = estadoDeCompra;
        this.fechaCambioEstado = fechaCambioEstado;
        this.precioTotal = precioTotal;
    }

    public Carrito() {
        this.publicacionesCarrito = new ArrayList<>();
    }

}
