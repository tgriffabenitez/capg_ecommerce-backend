package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {

    @Column(name = "descripcion")
    private String descripcion;

    @Transient
    private List<ProductoBase> productosBase;

    public Categoria() {
        this.descripcion = descripcion;
        productosBase = new ArrayList<>();
    }
}
