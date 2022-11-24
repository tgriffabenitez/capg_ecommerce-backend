package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productoBase")
public class ProductoBase extends EntidadPersistente {

    private String descripcion;

    private double precioBase;

    private LocalTime tiempoDeFabricacion;

    private Categoria categoria;

    private List<ProductoPersonalizado> productosPersonalizados;

    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    public ProductoBase() {
        this.productosPersonalizados = new ArrayList<>();
        this.posiblesPersonalizaciones = new ArrayList<>();
    }
}
