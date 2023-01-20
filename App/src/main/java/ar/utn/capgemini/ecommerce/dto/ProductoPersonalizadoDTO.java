package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductoPersonalizadoDTO {
    private String productoPersonalizadoUrl;
    private Integer productoBaseId;
    private Integer vendedorId;
}
