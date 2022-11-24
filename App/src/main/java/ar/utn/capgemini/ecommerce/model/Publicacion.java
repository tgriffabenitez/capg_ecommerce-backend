package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {

    private ESTADO estadoPublicacion;

    private LocalDate fechaCambioEstado;

    private List<PublicacionCarrito> publicacionesPorCarrito;

    private Vendedor vendedor;

    private ProductoPersonalizado productoPersonalizado;

    public Publicacion() {
        this.publicacionesPorCarrito = new ArrayList<>();
    }
}
