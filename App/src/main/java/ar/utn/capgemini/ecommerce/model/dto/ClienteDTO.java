package ar.utn.capgemini.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClienteDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String email;

    @NotBlank
    private String telefono;

    @NotBlank
    private String contrasenia;

    @NotBlank
    private String direccionCalle;

    @NotBlank
    private String direccionNumero;

    @NotBlank
    private String direccionPiso;

    @NotBlank
    private String direccionDepto;

//    private List<Carrito> carritos;
}
