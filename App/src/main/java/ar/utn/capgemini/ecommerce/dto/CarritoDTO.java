package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CarritoDTO {
    @Positive
    @NotNull
    private Integer clienteId;
    @NotNull
    private Integer metodoDePagoId;
    @NotNull
    @Positive
    private BigDecimal precioTotal;
    @NotNull
    private List<PublicacionCarritoDTO> publicaciones;
}
