package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESTADO estadoPublicacion;

    @NotNull
    @Past
    @Column(name = "fechaCambioEstado", columnDefinition = "DATE")
    private LocalDate fechaCambioEstado;

    @NotNull
    @OneToOne
    @JoinColumn(name = "productoPersonalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    public Publicacion(ESTADO estadoPublicacion, LocalDate fechaCambioEstado, ProductoPersonalizado productoPersonalizado) {
        this.estadoPublicacion = estadoPublicacion;
        this.fechaCambioEstado = fechaCambioEstado;
        this.productoPersonalizado = productoPersonalizado;
    }

    public Publicacion() {
    }

}