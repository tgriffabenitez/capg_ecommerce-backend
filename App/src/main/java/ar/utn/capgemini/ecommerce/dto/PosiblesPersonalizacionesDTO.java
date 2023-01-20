package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PosiblesPersonalizacionesDTO {
    private List<PosiblePersonalizacionDTO> posiblesPersonalizaciones;
}
