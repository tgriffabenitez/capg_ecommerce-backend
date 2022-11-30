package ar.utn.capgemini.ecommerce.modelo.entidades.productos;

import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "categoria")
    private String categoria;

    public Categoria() {
    }

}
