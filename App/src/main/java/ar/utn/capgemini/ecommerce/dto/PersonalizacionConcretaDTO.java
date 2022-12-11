package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class PersonalizacionConcretaDTO {
    @NotBlank
    private String detalle;
    @NotNull
    @Positive
    private Double precioPersonalizacion;
    @NotNull
    @Positive
    private Integer posiblePersonalizacionId;
}
