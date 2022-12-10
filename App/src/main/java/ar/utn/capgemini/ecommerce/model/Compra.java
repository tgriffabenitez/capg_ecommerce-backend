package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import ar.utn.capgemini.ecommerce.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "formaDePago")
    private PAGO metodoDePago;

    @Positive
    @Column(name = "precioTotal")
    private BigDecimal precioTotal;

    @PastOrPresent
    @Column(name = "fechaDeCompra", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeCompra;

    @OneToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;

    public Compra() {
    }
}
