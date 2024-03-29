package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoBase")
public class ProductoBase extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Positive
    @Column(name = "precioBase")
    private Double precioBase;

    @NotNull
    @Positive
    @Column(name = "tiempoDeFabricacion")
    private Integer tiempoDeFabricacion;

    @NotBlank
    @NotEmpty
    @Column(name = "url")
    private String productoBaseUrl;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToMany
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    @Column(name = "estado")
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
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    public ProductoBase(String descripcion, Double precioBase, Integer tiempoDeFabricacion, String productoBaseUrl, Categoria categoria) {
        this.posiblesPersonalizaciones = new ArrayList<>();
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoDeFabricacion = tiempoDeFabricacion;
        this.productoBaseUrl = productoBaseUrl;
        this.categoria = categoria;
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public ProductoBase() {
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

    public void agregarPosiblePersonalizacion(PosiblePersonalizacion posible) {
        this.posiblesPersonalizaciones.add(posible);
    }

}