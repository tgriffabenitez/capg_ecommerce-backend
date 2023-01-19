package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeAlta")
    private LocalDateTime fechaAlta;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeBaja")
    private LocalDateTime fechaBaja;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaModificacion")
    private LocalDateTime fechaModificacion;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

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
        this.fechaBaja = null;
        this.fechaModificacion = null;
        this.estaActivo = true;
    }

    public Cliente() {
        this.compras = new ArrayList<>();
        this.fechaAlta = LocalDateTime.now();
        this.fechaBaja = null;
        this.fechaModificacion = null;
        this.estaActivo = true;
    }

    public void agregarCompra(Compra compra) {
        this.compras.add(compra);
        compra.setCliente(this);
    }
}
