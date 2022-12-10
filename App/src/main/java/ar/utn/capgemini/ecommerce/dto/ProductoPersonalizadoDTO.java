package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductoPersonalizadoDTO {
    @NotBlank
    private String productoPersonalizadoUrl;
    @NotNull
    @Positive
    private Integer productoBaseId;
    @NotNull
    @Positive
    private Integer vendedorId;
}
