package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacionDTO {
    private String titulo;
    private String descripcion;
    private Integer productoPersonalizadoId;
    private Double precio;
}
