package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "categoria")
    private String categoria;

    @Column(name = "estado")
    private boolean estaActivo;

    @PastOrPresent
    @Column(name = "fechaDeAlta")
    private LocalDate fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja")
    private LocalDate fechaDeBaja;

    public Categoria(String categoria, boolean estaActivo, LocalDate fechaDeAlta, LocalDate fechaDeBaja) {
        this.categoria = categoria;
        this.estaActivo = true;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = fechaDeBaja;
    }

    public Categoria() {
    }

}
