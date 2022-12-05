package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
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
    private BigDecimal precioTotal;

    @Column(name = "estado")
    private boolean estaActivo;

    public ProductoPersonalizado(String productoPersonalizadoUrl, ProductoBase productoBase, Vendedor vendedor, LocalDateTime fechaDeAlta) {
        this.personalizacionesConcretas = new ArrayList<>();
        this.productoPersonalizadoUrl = productoPersonalizadoUrl;
        this.productoBase = productoBase;
        this.vendedor = vendedor;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
        this.estaActivo = true;
    }

    public ProductoPersonalizado() {
        this.personalizacionesConcretas = new ArrayList<>();
    }

    public void agregarPersonalizacionConcreta(PersonalizacionConcreta personalizacionConcreta) {
        this.personalizacionesConcretas.add(personalizacionConcreta);
    }

    public BigDecimal precioTotal() {
        BigDecimal precioTotal = this.productoBase.getPrecioBase();
        for (PersonalizacionConcreta personalizacionConcreta : this.personalizacionesConcretas) {
            precioTotal = precioTotal.add(personalizacionConcreta.getPrecioPersonalizacion());
        }
        return precioTotal;
    }

}
