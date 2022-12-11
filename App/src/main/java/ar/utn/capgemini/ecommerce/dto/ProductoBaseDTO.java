package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductoBaseDTO {
    @NotBlank
    private String descripcion;
    @NotNull
    @Positive
    private Double precioBase;
    @NotNull
    @Positive
    private Integer tiempoDeFabricacion;
    @NotBlank
    private String productoBaseUrl;
    @NotNull
    @Positive
    private Integer categoriaId;
}
