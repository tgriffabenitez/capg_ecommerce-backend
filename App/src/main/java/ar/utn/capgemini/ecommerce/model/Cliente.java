package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadPersistente {

    @Column(name = "clienteEstado")
    private boolean estaActivo;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Column(name = "apellido")
    private String apellido;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @Positive
    @Column(name = "telefono")
    private String telefono;

    @NotBlank
    @NotEmpty
    @Column(name = "contrasenia")
    private String contrasenia;

    @NotBlank
    @Column(name = "direccionCalle")
    private String direccionCalle;

    @NotBlank
    @Column(name = "direccionNumero")
    private String direccionNumero;

    @NotBlank
    @Column(name = "direccionPiso")
    private String direccionPiso;

    @NotBlank
    @Column(name = "direccionDepartamento")
    private String direccionDepto;

    @PastOrPresent
    @Column(name = "fechaDeAlta")
    private LocalDateTime fechaAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja")
    private LocalDateTime fechaBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion")
    private LocalDateTime fechaUltimaModificacion;


    public Cliente(String nombre, String apellido, String email, String telefono, String contrasenia, String direccionCalle, String direccionNumero, String direccionPiso, String direccionDepto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.direccionPiso = direccionPiso;
        this.direccionDepto = direccionDepto;
        this.estaActivo = true;
        this.fechaAlta = LocalDateTime.now();
        this.fechaBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public Cliente() {
        this.fechaAlta = LocalDateTime.now();
        this.fechaBaja = null;
        this.fechaUltimaModificacion = null;
        this.estaActivo = true;
    }
}
