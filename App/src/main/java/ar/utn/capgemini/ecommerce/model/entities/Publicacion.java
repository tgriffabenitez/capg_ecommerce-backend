package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADO estadoPublicacion;


    @NotNull
    @PastOrPresent
    @Column(name = "fechaCambioEstado", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCambioEstado;

    @NotNull
    @OneToOne
    @JoinColumn(name = "productoPersonalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    @NotNull
    @PositiveOrZero
    @Column(name = "precio")
    private BigDecimal precio;

    public Publicacion(ESTADO estadoPublicacion, LocalDateTime fechaCambioEstado, ProductoPersonalizado productoPersonalizado) {
        this.estadoPublicacion = estadoPublicacion;
        this.fechaCambioEstado = fechaCambioEstado;
        this.productoPersonalizado = productoPersonalizado;
        this.precio = BigDecimal.valueOf(0.0);

    }

    public Publicacion() {
    }

}