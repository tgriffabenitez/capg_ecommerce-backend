package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

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
    private BigDecimal subtotal;


    public PublicacionPorCarrito(Publicacion publicacion, Integer cantidad, BigDecimal subtotal) {
        this.cantidad = cantidad;
        this.publicacion = publicacion;
        this.subtotal = subtotal;
    }

    public PublicacionPorCarrito() {
    }


}
