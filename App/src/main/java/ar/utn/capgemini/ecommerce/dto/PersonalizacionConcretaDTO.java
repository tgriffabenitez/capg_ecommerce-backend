package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class PersonalizacionConcretaDTO {
    @NotBlank
    private String detalle;
    @NotNull
    @Positive
    private BigDecimal precioPersonalizacion;
    @NotNull
    @Positive
    private Integer posiblePersonalizacionId;
}
