package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductoBaseDTO {
    @NotBlank
    private String descripcion;
    @Positive
    private BigDecimal precioBase;
    @Positive
    private Integer tiempoDeFabricacion;
    @NotBlank
    private String productoBaseUrl;
    @Positive
    private Integer categoriaId;
}
