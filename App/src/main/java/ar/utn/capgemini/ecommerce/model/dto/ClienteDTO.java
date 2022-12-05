package ar.utn.capgemini.ecommerce.model.dto;

import ar.utn.capgemini.ecommerce.model.entities.Carrito;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    private String direccionDepartamento;
    @NotBlank
    private List<Carrito> carritos;
}
