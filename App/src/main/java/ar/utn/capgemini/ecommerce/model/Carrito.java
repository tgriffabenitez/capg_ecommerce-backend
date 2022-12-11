package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carrito")
public class Carrito extends EntidadPersistente {

    @NotNull
    @PositiveOrZero
    @Column(name = "precioTotal")
    private Double precioTotal;

    @OneToMany
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private List<PublicacionPorCarrito> publicacionesPorCarrito;


    public Carrito() {
        this.precioTotal = 0.0;
        this.publicacionesPorCarrito = new ArrayList<>();
    }

    public void agregarPublicacion(PublicacionPorCarrito publicacionPorCarrito) {
        this.publicacionesPorCarrito.add(publicacionPorCarrito);
    }

}
