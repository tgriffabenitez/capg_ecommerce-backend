package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductoPersonalizadoDTO {
    @NotBlank
    private String productoPersonalizadoUrl;
    @Positive
    private Integer productoBaseId;
    @Positive
    private Integer vendedorId;
}
