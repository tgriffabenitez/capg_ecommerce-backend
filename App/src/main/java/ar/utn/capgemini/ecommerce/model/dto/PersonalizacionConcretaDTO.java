package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class PersonalizacionConcretaDTO {
    @NotBlank
    private String detalle;
    @PositiveOrZero
    private Double precioPersonalizacionConcreta;
    @Positive
    private Integer posiblePersonalizacionId;
}
