package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PosiblePersonalizacionDTO {
    @NotBlank
    private Integer tipoPersonalizacionId;
    @NotBlank
    private Integer areaPersonalizacionId;

    public PosiblePersonalizacionDTO(Integer tipoPersonalizacionId, Integer areaPersonalizacionId) {
        this.tipoPersonalizacionId = tipoPersonalizacionId;
        this.areaPersonalizacionId = areaPersonalizacionId;
    }

    public PosiblePersonalizacionDTO() {
    }
}
