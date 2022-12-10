package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AreaPersonalizacionDTO {
    @NotBlank
    private String areaPersonalizacion;
}
