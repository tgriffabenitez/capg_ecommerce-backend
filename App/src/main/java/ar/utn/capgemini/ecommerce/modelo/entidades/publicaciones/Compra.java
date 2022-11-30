package ar.utn.capgemini.ecommerce.modelo.entidades.publicaciones;

import ar.utn.capgemini.ecommerce.modelo.enums.ESTADO;
import ar.utn.capgemini.ecommerce.modelo.enums.PAGO;
import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
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

    @NotNull
    @OneToMany
    @JoinColumn(name = "publicacionCarrito", referencedColumnName = "id")
    private List<PublicacionCarrito> publicacionesCarrito;

    public Compra() {
        this.publicacionesCarrito = new ArrayList<>();
    }

}
