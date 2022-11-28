package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoBase")
public class ProductoBase extends EntidadPersistente {

    @Column(name = "descripcionPrecioBase")
    private String descripcionProductoBase;

    @Column(name = "precioProductoBase")
    private double precioProductoBase;

    @Column(name = "tiempoDeFabricacion", columnDefinition = "TIME")
    private LocalTime tiempoDeFabricacion;

    @Column(name = "urlProductoBase")
    private String url;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToMany
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public ProductoBase() {
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

}
