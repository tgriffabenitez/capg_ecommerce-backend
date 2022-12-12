package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoPersonalizado")
public class ProductoPersonalizado extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "url")
    private String productoPersonalizadoUrl;

    @ManyToOne
    @JoinColumn(name = "productoBase_id", referencedColumnName = "id")
    private ProductoBase productoBase;

    @OneToMany
    @JoinColumn(name = "productoPersonalizado", referencedColumnName = "id")
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    @PastOrPresent
    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    @Positive
    @Column(name = "preciototal")
    private Double preciototal;

    @Column(name = "estado")
    private boolean estaActivo;

    public ProductoPersonalizado(String productoPersonalizadoUrl, ProductoBase productoBase, Vendedor vendedor) {
        this.personalizacionesConcretas = new ArrayList<>();
        this.productoPersonalizadoUrl = productoPersonalizadoUrl;
        this.productoBase = productoBase;
        this.vendedor = vendedor;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.estaActivo = true;
    }

    public ProductoPersonalizado() {
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.personalizacionesConcretas = new ArrayList<>();
    }

    public void agregarPersonalizacionConcreta(PersonalizacionConcreta personalizacionConcreta) {
        this.personalizacionesConcretas.add(personalizacionConcreta);
    }

    public Double calcularPrecioTotal() {
        Double preciototal = this.productoBase.getPrecioBase();
        for (PersonalizacionConcreta personalizacionConcreta : this.personalizacionesConcretas) {
            preciototal = preciototal + personalizacionConcreta.getPrecioPersonalizacionConcreta();
        }
        this.preciototal = preciototal;
        return this.preciototal;
    }

}
