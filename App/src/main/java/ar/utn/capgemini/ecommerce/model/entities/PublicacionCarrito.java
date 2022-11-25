package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
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
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    public PublicacionCarrito() {
    }

}
