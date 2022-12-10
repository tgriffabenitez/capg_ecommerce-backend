package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class PublicacionCarritoDTO {

    @NotNull
    private Integer cantidad;

    @NotNull
    private Integer publicacionId;

    @NotNull
    private BigDecimal subtotal;
}
