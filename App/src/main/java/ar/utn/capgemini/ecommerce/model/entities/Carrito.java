package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carrito")
public class Carrito extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    private ESTADO estadoCarrito;

    @Column(name = "fechaCambioEstado", columnDefinition = "DATE")
    private LocalDate fechaCambioEstado;

    @OneToMany(mappedBy = "carrito")
    @JsonBackReference
    private List<PublicacionCarrito> publicacionesPorCarrito;

    @OneToOne(mappedBy = "carrito")
    private Compra compra;

    @OneToOne(mappedBy = "carrito")
    private Cliente cliente;

    public Carrito() {
        this.publicacionesPorCarrito = new ArrayList<>();
    }

    public void agregarPublicacionCarrito(PublicacionCarrito publicacionCarrito){
        this.publicacionesPorCarrito.add(publicacionCarrito);
        publicacionCarrito.setCarrito(this);
    }
}
