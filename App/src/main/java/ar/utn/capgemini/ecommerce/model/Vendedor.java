package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor extends EntidadPersistente {

    private String nombre;

    private String apellido;

    private String tienda;

    private List<MetodoDePagoPorVendedor> metodosDePago;

    private List<PersonalizacionConcreta> personalizacionesConcretas;

    public Vendedor() {
        this.metodosDePago = new ArrayList<>();
        this.personalizacionesConcretas = new ArrayList<>();
    }
}
