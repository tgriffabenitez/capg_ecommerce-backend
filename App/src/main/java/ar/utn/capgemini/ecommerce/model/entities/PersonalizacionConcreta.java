package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Entity
@Table(name = "personalizacionConcreta")
public class PersonalizacionConcreta extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "detalle")
    private String detalle;

    @NotNull
    @Positive
    @Column(name = "precioPersonalizacionConcreta")
    private double precioPersonalizacionConcreta;

    @ManyToOne
    @JoinColumn(name = "posiblePersonalizacion_id", referencedColumnName = "id")
    private PosiblePersonalizacion posiblePersonalizacion;

    public PersonalizacionConcreta(String detalle, double precioPersonalizacionConcreta, PosiblePersonalizacion posiblePersonalizacion) {
        this.detalle = detalle;
        this.precioPersonalizacionConcreta = precioPersonalizacionConcreta;
        this.posiblePersonalizacion = posiblePersonalizacion;
    }

    public PersonalizacionConcreta() {
    }

}
