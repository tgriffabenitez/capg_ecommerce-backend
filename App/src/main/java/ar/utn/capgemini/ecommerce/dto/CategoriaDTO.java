package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CategoriaDTO {
    @NotNull
    @Positive
    private Integer categoria;
}
