package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import ar.utn.capgemini.ecommerce.utils.PAGO;
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
    @Past
    @Column(name = "fechaCambioEstado", columnDefinition = "DATETIME")
    private LocalDateTime fechaCambioEstado;

    @NotNull
    @Positive
    @Column(name = "precioTotal")
    private BigDecimal precioTotal;

    @OneToMany
    @JoinColumn(name = "publicacionCarrito", referencedColumnName = "id")
    private List<PublicacionPorCarrito> publicacionesPorCarrito;

    public Carrito(PAGO metodoDePago, BigDecimal precioTotal) {
        this.publicacionesPorCarrito = new ArrayList<>();
        this.metodoDePago = metodoDePago;
        this.fechaCambioEstado = LocalDateTime.now();
        this.precioTotal = precioTotal;
    }

    public Carrito() {
        this.publicacionesPorCarrito = new ArrayList<>();
    }

    public void agregarPublicacion(PublicacionPorCarrito publicacionPorCarrito) {
        this.publicacionesPorCarrito.add(publicacionPorCarrito);
    }

}
