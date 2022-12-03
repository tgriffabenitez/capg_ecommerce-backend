package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria extends EntidadPersistente {

    @Column(name = "categoria")
    private String categoria;

    @NotNull
    @Column(name = "estado")
    private boolean estaActivo;

    @PastOrPresent
    @Column(name = "fechaDeAlta")
    private LocalDate fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja")
    private LocalDate fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion")
    private LocalDate fechaUltimaModificacion;

    public Categoria(String categoria, LocalDate fechaDeAlta) {
        this.categoria = categoria;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.estaActivo = true;
    }

    public Categoria() {
    }

}
