package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    private ESTADO estadoPublicacion;

    @Column(name = "fechaCambioEstado", columnDefinition = "DATE")
    private LocalDate fechaCambioEstado;

    @OneToMany(mappedBy = "publicacion")
    @JsonBackReference
    private List<PublicacionCarrito> publicacionesPorCarrito;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    @OneToOne
    @JoinColumn(name = "productoPersonalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    public Publicacion() {
        this.publicacionesPorCarrito = new ArrayList<>();
    }

    public void agregarPublicacionCarrito(PublicacionCarrito publicacionCarrito){
        this.publicacionesPorCarrito.add(publicacionCarrito);
        publicacionCarrito.setPublicacion(this);
    }
}
