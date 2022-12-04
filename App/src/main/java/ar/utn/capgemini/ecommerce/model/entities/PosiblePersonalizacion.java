package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
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

    @PastOrPresent
    @Column(name = "fechaDeAlta")
    private LocalDateTime fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja")
    private LocalDateTime fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion")
    private LocalDateTime fechaUltimaModificacion;

    public PosiblePersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion, LocalDateTime fechaDeAlta) {
        this.tipoPersonalizacion = tipoPersonalizacion;
        this.areaPersonalizacion = areaPersonalizacion;
        this.estaActivo = true;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public PosiblePersonalizacion() {
    }

}
