package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tipoPersonalizacion")
public class TipoPersonalizacion extends EntidadPersistente {

    private String descripcion;

    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public TipoPersonalizacion() {
        this.posiblesPersonalizaciones = new ArrayList<>();
    }
}
