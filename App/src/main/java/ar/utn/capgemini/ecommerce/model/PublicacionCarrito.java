package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "publicacionPorCarrito")
public class PublicacionCarrito extends EntidadPersistente {

    @Column(name = "cantidadPublicaciones")
    private Integer cantidadPublicaciones;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;

    public PublicacionCarrito() {
    }
}
