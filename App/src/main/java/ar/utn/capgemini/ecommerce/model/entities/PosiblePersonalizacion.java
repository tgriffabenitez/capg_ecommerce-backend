package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
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

    public PosiblePersonalizacion() {
    }

}
