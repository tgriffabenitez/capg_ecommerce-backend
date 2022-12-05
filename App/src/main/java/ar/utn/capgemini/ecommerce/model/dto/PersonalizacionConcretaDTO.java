package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class PersonalizacionConcretaDTO {
    @NotBlank
    private String detalle;
    @PositiveOrZero
    private BigDecimal precioPersonalizacion;
    @Positive
    private Integer posiblePersonalizacionId;
}
