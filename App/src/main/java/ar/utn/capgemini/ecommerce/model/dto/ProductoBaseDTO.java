package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductoBaseDTO {
    @NotBlank
    private String descripcion;
    @Positive
    private Double precioBase;
    @Positive
    private Integer tiempoDeFabricacion;
    @NotBlank
    private String productoBaseurl;
    @Positive
    private Integer categoriaId;

    public ProductoBaseDTO(String descripcion, Double precioBase, Integer tiempoDeFabricacion, String productoBaseurl, Integer categoriaId) {
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoDeFabricacion = tiempoDeFabricacion;
        this.productoBaseurl = productoBaseurl;
        this.categoriaId = categoriaId;
    }

    public ProductoBaseDTO() {
    }
}