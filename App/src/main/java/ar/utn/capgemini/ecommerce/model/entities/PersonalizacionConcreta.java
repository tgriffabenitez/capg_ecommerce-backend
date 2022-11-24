package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "personalizacionConcreta")
public class PersonalizacionConcreta extends EntidadPersistente {

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "precioPersonalizacionConcreta")
    private Integer precioPersonalizacionConcreta;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "posiblePersonalizacion_id", referencedColumnName = "id")
    private PosiblePersonalizacion posiblePersonalizacion;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "productoPersonalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    public PersonalizacionConcreta() {
    }
}
