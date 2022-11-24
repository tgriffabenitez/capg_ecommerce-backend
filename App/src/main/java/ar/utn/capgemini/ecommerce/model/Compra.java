package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.enums.ESTADO;
import ar.utn.capgemini.ecommerce.model.enums.PAGO;
import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {

    private LocalDate fechaDeCompra;

    private LocalTime horaDeCompra;

    private PAGO metodoDePago;

    private ESTADO estadoDeCompra;

    private LocalDate fechaCambioEstado;

    private Carrito carrito;

    private Cliente cliente;

    private Factura factura;

    public Compra() {
    }
}
