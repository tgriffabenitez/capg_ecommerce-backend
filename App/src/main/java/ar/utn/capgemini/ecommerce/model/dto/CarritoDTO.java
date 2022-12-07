package ar.utn.capgemini.ecommerce.model.dto;

import ar.utn.capgemini.ecommerce.model.entities.Carrito;
import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import ar.utn.capgemini.ecommerce.model.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CarritoDTO {
    @NotNull
    @PastOrPresent
    private LocalDateTime fechaDeCompra;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PAGO metodoDePago;
    @NotNull
    private ESTADO estadoDeCompra;
    @NotNull
    @Past
    private LocalDateTime fechaCambioEstado;
    @NotNull
    @Positive
    private BigDecimal precioTotal;
    @NotNull
    private List<Carrito> carritos;
}
