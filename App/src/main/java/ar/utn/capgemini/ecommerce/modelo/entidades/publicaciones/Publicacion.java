package ar.utn.capgemini.ecommerce.modelo.entidades.publicaciones;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.ProductoPersonalizado;
import ar.utn.capgemini.ecommerce.modelo.enums.ESTADO;
import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
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

    public Publicacion() {
    }

}