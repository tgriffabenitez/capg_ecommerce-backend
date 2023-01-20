package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonalizacionConcretaDTO {
    private String detalle;
    private Double precioPersonalizacion;
    private Integer posiblePersonalizacionId;
}
