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
@Table(name = "areaPersonalizacion")
public class AreaPersonalizacion extends EntidadPersistente {

    private String areaPersonalizacion;

    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public AreaPersonalizacion() {
        this.posiblesPersonalizaciones = new ArrayList<>();
    }
}
