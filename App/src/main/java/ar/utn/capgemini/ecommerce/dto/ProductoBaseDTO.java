package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductoBaseDTO {
    @NotBlank
    private String descripcion;
    @NotNull
    @Positive
    private BigDecimal precioBase;
    @NotNull
    @Positive
    private Integer tiempoDeFabricacion;
    @NotBlank
    private String productoBaseUrl;
    @NotNull
    @Positive
    private Integer categoriaId;
}
