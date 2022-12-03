package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PosiblePersonalizacionDTO {
    @NotBlank
    private String tipoPersonalizacion;
    @NotBlank
    private String areaPersonalizacion;

    public PosiblePersonalizacionDTO(String tipoPersonalizacion, String areaPersonalizacion) {
        this.tipoPersonalizacion = tipoPersonalizacion;
        this.areaPersonalizacion = areaPersonalizacion;
    }

    public PosiblePersonalizacionDTO() {
    }
}
