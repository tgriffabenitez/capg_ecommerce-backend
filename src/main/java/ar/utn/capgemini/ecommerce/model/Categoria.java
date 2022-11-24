package ar.utn.capgemini.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {

    private String descripcion;
    private List<ProductoBase> productosBase;

    public Categoria(){
        this.productosBase = new ArrayList<>();
    }

}
