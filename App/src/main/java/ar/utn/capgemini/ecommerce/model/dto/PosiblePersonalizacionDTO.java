package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class PosiblePersonalizacionDTO {
    @NotNull
    @Positive
    private Integer tipoPersonalizacion;

    @NotNull
    @Positive
    private Integer areaPersonalizacion;
}
