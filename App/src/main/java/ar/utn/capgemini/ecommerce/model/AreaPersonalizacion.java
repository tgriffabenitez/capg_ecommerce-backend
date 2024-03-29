package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "areaPersonalizacion")
public class AreaPersonalizacion extends EntidadPersistente {

    @Column(name = "estado")
    private boolean estaActivo;

    @Column(name = "area")
    private String area;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    public AreaPersonalizacion(String area) {
        this.area = area;
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaUltimaModificacion = null;
        this.fechaDeBaja = null;
    }

    public AreaPersonalizacion() {
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaUltimaModificacion = null;
        this.fechaDeBaja = null;
    }

}
