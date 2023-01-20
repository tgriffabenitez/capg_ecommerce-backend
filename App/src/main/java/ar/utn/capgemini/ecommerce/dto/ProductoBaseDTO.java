package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductoBaseDTO {
    private String descripcion;
    private Double precioBase;
    private Integer tiempoDeFabricacion;
    private String productoBaseUrl;
    private Integer categoriaId;
}
