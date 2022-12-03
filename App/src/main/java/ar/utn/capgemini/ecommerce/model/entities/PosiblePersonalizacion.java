package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {

    @Column(name = "estado")
    private boolean estaActivo;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipoPersonalizacion_id", referencedColumnName = "id")
    private TipoPersonalizacion tipoPersonalizacion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "areaPersonalizacion", referencedColumnName = "id")
    private AreaPersonalizacion areaPersonalizacion;

    public PosiblePersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion, boolean estaActivo) {
        this.tipoPersonalizacion = tipoPersonalizacion;
        this.areaPersonalizacion = areaPersonalizacion;
        this.estaActivo = true;
    }

    public PosiblePersonalizacion() {
    }

}
