package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.enums.PAGO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @OneToOne()
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToOne(mappedBy = "compra")
    private Factura factura;

    public Compra() {
    }
}
