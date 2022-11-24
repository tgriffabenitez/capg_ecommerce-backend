package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carrito")
public class Carrito extends EntidadPersistente {

    private ESTADO estadoCarrito;

    private LocalDate fechaCambioEstado;

    private List<PublicacionCarrito> publicacionesPorCarrito;

    private Compra compra;

    private Cliente cliente;

    public Carrito() {
        this.publicacionesPorCarrito = new ArrayList<>();
    }
}
