package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "posiblePersonalizacion")
public class PosiblePersonalizacion extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;

    @ManyToOne
    @JoinColumn(name = "tipoPersonalizacion_id", referencedColumnName = "id")
    private TipoPersonalizacion tipoPersonalizacion;

    @ManyToOne
    @JoinColumn(name = "areaPersonalizacion", referencedColumnName = "id")
    private AreaPersonalizacion areaPersonalizacion;

    @OneToMany(mappedBy = "posiblePersonalizacion")
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    public PosiblePersonalizacion() {
        this.personalizacionesConcretas = new ArrayList<>();
    }

    public void agregarPosiblePersonalizacion(PersonalizacionConcreta personalizacionConcreta){
        this.personalizacionesConcretas.add(personalizacionConcreta);
        personalizacionConcreta.setPosiblePersonalizacion(this);
    }
}
