package ar.utn.capgemini.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClienteDTO {
    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    private String contrasenia;

    private String direccionCalle;

    private String direccionNumero;

    private String direccionPiso;

    private String direccionDepto;
}
