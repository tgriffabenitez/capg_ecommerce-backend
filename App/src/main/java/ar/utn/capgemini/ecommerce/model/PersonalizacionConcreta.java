package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "personalizacionConcreta")
public class PersonalizacionConcreta extends EntidadPersistente {

    private String detalle;

    private Integer precioPersonalizacionConcreta;

    private PosiblePersonalizacion posiblePersonalizacion;

    private Vendedor vendedor;

    private ProductoPersonalizado productoPersonalizado;

    public PersonalizacionConcreta() {
    }
}
