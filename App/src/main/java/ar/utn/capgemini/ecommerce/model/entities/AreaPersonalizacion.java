package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "areaPersonalizacion")
public class AreaPersonalizacion extends EntidadPersistente {

    @Column(name = "descripcionAreaPersonalizacion")
    private String descripcionAreaPersonalizacion;

    public AreaPersonalizacion() {
    }

}
