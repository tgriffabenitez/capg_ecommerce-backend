package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "tienda")
    private String tienda;

    @ManyToMany(cascade = CascadeType.ALL)
    @Column(name = "metodosDePago")
    private List<MetodoDePago> metodosDePago;

    public Vendedor() {
        this.metodosDePago = new ArrayList<>();
    }

}
