package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.ESTADO;
import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private ESTADO estadoPublicacion;

    @NotNull
    @Column(name = "dadoDeBaja")
    private boolean estaActivo;

    @PastOrPresent
    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaCambioEstado", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCambioEstado;

    @NotNull
    @OneToOne
    @JoinColumn(name = "productoPersonalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    @NotNull
    @PositiveOrZero
    @Column(name = "precio")
    private BigDecimal precio;

    public Publicacion(String titulo, String descripcion, ESTADO estadoPublicacion, ProductoPersonalizado productoPersonalizado, BigDecimal precio) {
        this.estadoPublicacion = estadoPublicacion;
        this.productoPersonalizado = productoPersonalizado;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.precio = precio;
        this.fechaDeAlta = LocalDateTime.now();
        this.estaActivo = true;
        this.fechaDeBaja = null;
        this.fechaCambioEstado = null;
    }

    public Publicacion() {
        this.precio = BigDecimal.valueOf(0.0);
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaCambioEstado = null;
        this.estaActivo = true;
    }

}