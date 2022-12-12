package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "publicacion")
public class Publicacion extends EntidadPersistente {

    @NotNull
    @Column(name = "titulo")
    private String titulo;

    @NotNull
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "dadoDeBaja")
    private boolean estaActivo;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaCambioEstado", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCambioEstado;

    @NotNull
    @OneToOne
    @JoinColumn(name = "productoPersonalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    public Publicacion(String titulo, String descripcion, ProductoPersonalizado productoPersonalizado, Vendedor vendedor) {
        this.vendedor = vendedor;
        this.productoPersonalizado = productoPersonalizado;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.fechaDeAlta = LocalDateTime.now();
        this.estaActivo = true;
        this.fechaDeBaja = null;
        this.fechaCambioEstado = null;
    }

    public Publicacion() {
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaCambioEstado = null;
        this.estaActivo = true;
    }
}