package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "areaPersonalizacion")
public class AreaPersonalizacion extends EntidadPersistente {

    @Column(name = "estado")
    private boolean estaActivo;

    @Column(name = "area")
    private String area;

    @PastOrPresent
    private LocalDate fechaDeAlta;

    @PastOrPresent
    private LocalDate fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion")
    private LocalDate fechaUltimaModificacion;

    public AreaPersonalizacion(String area, LocalDate fechaDeAlta) {
        this.area = area;
        this.estaActivo = true;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaUltimaModificacion = null;
        this.fechaDeBaja = null;
    }

    public AreaPersonalizacion() {
    }

}
