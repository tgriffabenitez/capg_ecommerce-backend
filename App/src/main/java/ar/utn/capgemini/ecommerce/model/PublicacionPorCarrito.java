package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "publicacionPorCarrito")
public class PublicacionPorCarrito extends EntidadPersistente {

    @NotNull
    @PositiveOrZero
    @Column(name = "cantidadPublicaciones")
    private Integer cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    @NotNull
    @PositiveOrZero
    @Column(name = "subtotal")
    private Double subtotal;

    @NotNull
    @PositiveOrZero
    @Column(name = "precioUnitario")
    private Double precioUnitario;

    public PublicacionPorCarrito() {
    }


}
