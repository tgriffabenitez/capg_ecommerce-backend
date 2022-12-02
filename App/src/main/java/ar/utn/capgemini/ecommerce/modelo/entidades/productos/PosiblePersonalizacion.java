package ar.utn.capgemini.ecommerce.modelo.entidades.productos;

import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipoPersonalizacion_id", referencedColumnName = "id")
    private TipoPersonalizacion tipoPersonalizacion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "areaPersonalizacion", referencedColumnName = "id")
    private AreaPersonalizacion areaPersonalizacion;

    public PosiblePersonalizacion(TipoPersonalizacion tipoPersonalizacion, AreaPersonalizacion areaPersonalizacion) {
        this.tipoPersonalizacion = tipoPersonalizacion;
        this.areaPersonalizacion = areaPersonalizacion;
    }

    public PosiblePersonalizacion() {
    }

}
