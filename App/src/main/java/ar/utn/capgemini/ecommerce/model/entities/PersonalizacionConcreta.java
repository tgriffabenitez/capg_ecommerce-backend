package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "personalizacionConcreta")
public class PersonalizacionConcreta extends EntidadPersistente {

    @Column(name = "estado")
    private boolean estaActivo;

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


    @PastOrPresent
    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    public PersonalizacionConcreta(String detalle, double precioPersonalizacionConcreta, PosiblePersonalizacion posiblePersonalizacion, LocalDateTime fechaDeAlta) {
        this.detalle = detalle;
        this.precioPersonalizacionConcreta = precioPersonalizacionConcreta;
        this.posiblePersonalizacion = posiblePersonalizacion;
        this.estaActivo = true;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public PersonalizacionConcreta() {
    }

}
