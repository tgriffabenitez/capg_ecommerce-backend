package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PosiblePersonalizacionDTO {
    @NotBlank
    private Integer tipoPersonalizacion;
    @NotBlank
    private Integer areaPersonalizacion;

    public PosiblePersonalizacionDTO(Integer tipoPersonalizacion, Integer areaPersonalizacion) {
        this.tipoPersonalizacion = tipoPersonalizacion;
        this.areaPersonalizacion = areaPersonalizacion;
    }

    public PosiblePersonalizacionDTO() {
    }
}
