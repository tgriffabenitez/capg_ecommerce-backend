package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
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

    @Column(name = "estado")
    private boolean estaActivo;

    @NotBlank
    @NotEmpty
    @Column(name = "categoria")
    private String categoria;

    public Categoria(String categoria, boolean estaActivo) {
        this.categoria = categoria;
        this.estaActivo = true;
    }

    public Categoria() {
    }

}
