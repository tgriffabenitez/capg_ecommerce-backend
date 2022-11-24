package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "tipoPersonalizacion_id", referencedColumnName = "id")
    private TipoPersonalizacion tipoPersonalizacion;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "areaPersonalizacion", referencedColumnName = "id")
    private AreaPersonalizacion areaPersonalizacion;

    @OneToMany(mappedBy = "posiblePersonalizacion")
    @JsonBackReference
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    public PosiblePersonalizacion() {
        this.personalizacionesConcretas = new ArrayList<>();
    }

    public void agregarPosiblePersonalizacion(PersonalizacionConcreta personalizacionConcreta){
        this.personalizacionesConcretas.add(personalizacionConcreta);
        personalizacionConcreta.setPosiblePersonalizacion(this);
    }
}
