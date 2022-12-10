package ar.utn.capgemini.ecommerce.dto;

import ar.utn.capgemini.ecommerce.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CompraDTO {

    // info del cliente
    @NotNull
    @Positive
    private Integer clienteId;

    // info de la compra
    @NotNull
    private PAGO metodoDePago;

    // info del carrito
    @NotNull
    private List<PublicacionCarritoDTO> publicaciones;
    @NotNull
    @Positive
    private BigDecimal total;
}
