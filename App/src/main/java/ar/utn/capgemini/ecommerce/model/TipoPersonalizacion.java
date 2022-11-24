package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tipoPersonalizacion")
public class TipoPersonalizacion extends EntidadPersistente {

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "tipoPersonalizacion")
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public TipoPersonalizacion() {
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

    public void agregarPosiblePersonalizacion(PosiblePersonalizacion posiblePersonalizacion){
        this.posiblesPersonalizaciones.add(posiblePersonalizacion);
        posiblePersonalizacion.setTipoPersonalizacion(this);
    }
}
