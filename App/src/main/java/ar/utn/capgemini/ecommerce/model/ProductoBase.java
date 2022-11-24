package ar.utn.capgemini.ecommerce.model;

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

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precioBase")
    private double precioBase;

    @Column(name = "tiempoDeFabricacion", columnDefinition = "TIME")
    private LocalTime tiempoDeFabricacion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

   @OneToMany(mappedBy = "productoBase")
    private List<ProductoPersonalizado> productosPersonalizados;

    @OneToMany(mappedBy = "productoBase")
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public ProductoBase() {
        this.productosPersonalizados = new ArrayList<>();
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

    public void agregarProductoPersonalizado(ProductoPersonalizado productoPersonalizado){
        this.productosPersonalizados.add(productoPersonalizado);
        productoPersonalizado.setProductoBase(this);
    }

    public void agregarPosiblePersonalizacion(PosiblePersonalizacion posiblePersonalizacion){
        this.posiblesPersonalizaciones.add(posiblePersonalizacion);
        posiblePersonalizacion.setProductoBase(this);
    }
}
