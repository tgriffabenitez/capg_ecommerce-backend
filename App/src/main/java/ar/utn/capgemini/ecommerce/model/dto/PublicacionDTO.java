package ar.utn.capgemini.ecommerce.model.dto;

import ar.utn.capgemini.ecommerce.model.utils.ESTADO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class PublicacionDTO {
    @NotNull
    private String titulo;
    @NotNull
    private String descripcion;
    @NotNull
    private ESTADO estadoPublicacion;
    @NotNull
    private Integer productoPersonalizadoId;
    @NotNull
    @PositiveOrZero
    private BigDecimal precio;
}
