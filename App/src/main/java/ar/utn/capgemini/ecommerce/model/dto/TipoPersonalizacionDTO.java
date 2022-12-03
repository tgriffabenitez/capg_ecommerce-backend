package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TipoPersonalizacionDTO {
    @NotBlank
    private String tipoPersonalizacion;

    public TipoPersonalizacionDTO(String tipoPersonalizacion) {
        this.tipoPersonalizacion = tipoPersonalizacion;
    }

    public TipoPersonalizacionDTO() {
    }
}
