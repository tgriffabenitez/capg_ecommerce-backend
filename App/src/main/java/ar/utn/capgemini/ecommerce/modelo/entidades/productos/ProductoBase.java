package ar.utn.capgemini.ecommerce.modelo.entidades.productos;

import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoBase")
public class ProductoBase extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Positive
    @Column(name = "precioBase")
    private double precioBase;

    @NotNull
    @Positive
    @Column(name = "tiempoDeFabricacion")
    private Integer tiempoDeFabricacion;

    @NotBlank
    @NotEmpty
    @Column(name = "url")
    private String url;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @NotNull
    @OneToMany
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public ProductoBase() {
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

}