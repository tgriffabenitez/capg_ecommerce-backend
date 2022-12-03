package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoriaDTO {
    @NotBlank
    private String categoria;

    public CategoriaDTO(String categoria) {
        this.categoria = categoria;
    }

    public CategoriaDTO() {
    }
}
