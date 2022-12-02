package ar.utn.capgemini.ecommerce.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "publicacionPorCarrito")
public class PublicacionCarrito extends EntidadPersistente {

    @NotNull
    @PositiveOrZero
    @Column(name = "cantidadPublicaciones")
    private Integer cantidadPublicaciones;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    public PublicacionCarrito(Integer cantidadPublicaciones, Publicacion publicacion) {
        this.cantidadPublicaciones = cantidadPublicaciones;
        this.publicacion = publicacion;
    }

    public PublicacionCarrito() {
    }

}
