package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
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

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;

    @OneToMany
    @JoinColumn(name = "productoPersonalizado", referencedColumnName = "id")
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    public ProductoPersonalizado() {
        this.personalizacionesConcretas = new ArrayList<>();
    }

}
