package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoPersonalizado")
public class ProductoPersonalizado extends EntidadPersistente {

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;

    @OneToMany(mappedBy = "productoPersonalizado")
    @JsonBackReference
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    @OneToOne(mappedBy = "productoPersonalizado")
    private Publicacion publicacion;

    public ProductoPersonalizado() {
        this.personalizacionesConcretas = new ArrayList<>();
    }

    public void agregarPersonalizacionConcreta(PersonalizacionConcreta personalizacionConcreta){
        this.personalizacionesConcretas.add(personalizacionConcreta);
        personalizacionConcreta.setProductoPersonalizado(this);
    }
}
