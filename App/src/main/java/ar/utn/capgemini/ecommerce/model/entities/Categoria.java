package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

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
    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    public Categoria(String categoria) {
        this.categoria = categoria;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.estaActivo = true;
    }

    public Categoria() {
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.estaActivo = true;
    }

}
