package ar.utn.capgemini.ecommerce.modelo.entidades.personas;

import ar.utn.capgemini.ecommerce.modelo.entidades.publicaciones.Compra;
import ar.utn.capgemini.ecommerce.modelo.entidades.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "nombre")
    private String nombreCliente;

    @NotBlank
    @NotEmpty
    @Column(name = "apellido")
    private String apellidoCliente;

    @NotBlank
    @NotEmpty
    @Email
    @Column(name = "email")
    private String emailCliente;

    @NotBlank
    @NotEmpty
    @Column(name = "contrasenia")
    private String contrasenia;

    @OneToMany
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private List<Compra> compras;

    public Cliente(String nombreCliente, String apellidoCliente, String emailCliente, String contrasenia) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.emailCliente = emailCliente;
        this.contrasenia = contrasenia;
    }

    public Cliente() {
        this.compras = new ArrayList<>();
    }

}
