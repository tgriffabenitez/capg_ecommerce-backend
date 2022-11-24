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
@Table(name = "productoPersonalizado")
public class ProductoPersonalizado extends EntidadPersistente {

    private ProductoBase productoBase;

    private List<PersonalizacionConcreta> personalizacionesConcretas;

    private Publicacion publicacion;

    public ProductoPersonalizado() {
        this.personalizacionesConcretas = new ArrayList<>();
    }
}
