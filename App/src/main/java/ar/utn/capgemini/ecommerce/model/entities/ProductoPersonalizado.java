package ar.utn.capgemini.ecommerce.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoPersonalizado")
public class ProductoPersonalizado extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "url")
    private String productoPersonalizadoUrl;

    @ManyToOne
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;

    @OneToMany
    @JoinColumn(name = "productoPersonalizado", referencedColumnName = "id")
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    public ProductoPersonalizado(String productoPersonalizadoUrl, ProductoBase productoBase, Vendedor vendedor) {
        this.productoPersonalizadoUrl = productoPersonalizadoUrl;
        this.productoBase = productoBase;
        this.vendedor = vendedor;
    }

    public ProductoPersonalizado() {
        this.personalizacionesConcretas = new ArrayList<>();
    }

}