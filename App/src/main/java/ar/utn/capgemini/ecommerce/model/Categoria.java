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
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<ProductoBase> productosBase;

    public Categoria() {
        productosBase = new ArrayList<>();
    }

    public void agregarProductoBase(ProductoBase productosBase){
        this.productosBase.add(productosBase);
        productosBase.setCategoria(this);
    }
}
