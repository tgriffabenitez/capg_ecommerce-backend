package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
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
    private double precioPersonalizacionConcreta;

    @ManyToOne
    @JoinColumn(name = "posiblePersonalizacion_id", referencedColumnName = "id")
    private PosiblePersonalizacion posiblePersonalizacion;

    public PersonalizacionConcreta() {
    }

}
