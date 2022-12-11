package ar.utn.capgemini.ecommerce.dto;

import ar.utn.capgemini.ecommerce.utils.ESTADO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
    private Double precio;
}
