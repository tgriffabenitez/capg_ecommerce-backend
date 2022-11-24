package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {

    private ProductoBase productoBase;

    private TipoPersonalizacion tipoPersonalizacion;

    private AreaPersonalizacion areaPersonalizacion;

    private List<PersonalizacionConcreta> personalizacionesConcretas;

    public PosiblePersonalizacion() {
        this.personalizacionesConcretas = new ArrayList<>();
    }
}
