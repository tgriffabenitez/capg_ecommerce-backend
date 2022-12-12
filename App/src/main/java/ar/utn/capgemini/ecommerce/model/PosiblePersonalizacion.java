package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {

    @Column(name = "estado")
    private boolean estaActivo;
    @ManyToOne
    @JoinColumn(name = "tipoPersonalizacion_id", referencedColumnName = "id")
    private TipoPersonalizacion tipoPersonalizacion;

    @ManyToOne
    @JoinColumn(name = "areaPersonalizacion", referencedColumnName = "id")
    private AreaPersonalizacion areaPersonalizacion;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeAlta")
    private LocalDateTime fechaDeAlta;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeBaja")
    private LocalDateTime fechaDeBaja;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaUltimaModificacion")
    private LocalDateTime fechaUltimaModificacion;

    public PosiblePersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion) {
        this.tipoPersonalizacion = tipoPersonalizacion;
        this.areaPersonalizacion = areaPersonalizacion;
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public PosiblePersonalizacion() {
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

}
