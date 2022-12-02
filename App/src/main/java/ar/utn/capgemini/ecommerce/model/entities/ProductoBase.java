package ar.utn.capgemini.ecommerce.model.entities;

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
    private String precioBaseUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToMany
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public ProductoBase(String descripcion, double precioBase, Integer tiempoDeFabricacion, String precioBaseUrl, Categoria categoria) {
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoDeFabricacion = tiempoDeFabricacion;
        this.precioBaseUrl = precioBaseUrl;
        this.categoria = categoria;
    }

    public ProductoBase() {
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

}