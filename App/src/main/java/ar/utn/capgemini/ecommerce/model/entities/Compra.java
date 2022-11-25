package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.enums.PAGO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {

    @Column(name = "fechaDeCompra", columnDefinition = "DATE")
    private LocalDate fechaDeCompra;

    @Column(name = "horaDeCompra", columnDefinition = "TIME")
    private LocalTime horaDeCompra;

    @Enumerated(EnumType.STRING)
    private PAGO metodoDePago;

    @Enumerated(EnumType.STRING)
    private ESTADO estadoDeCompra;

    @Column(name = "fechaCambioEstado", columnDefinition = "DATE")
    private LocalDate fechaCambioEstado;

    @Column(name = "precioTotal")
    private double precioTotal;

    @OneToMany
    @JoinColumn(name = "publicacionCarrito", referencedColumnName = "id")
    private List<PublicacionCarrito> publicacionesCarrito;

    public Compra() {
        this.publicacionesCarrito = new ArrayList<>();
    }

}
