package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
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
    private List<Carrito> carritos;

    public Cliente(String nombreCliente, String apellidoCliente, String emailCliente, String contrasenia) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.emailCliente = emailCliente;
        this.contrasenia = contrasenia;
    }

    public Cliente() {
        this.carritos = new ArrayList<>();
    }

    public void agregarCompra(Carrito carrito) {
        this.carritos.add(carrito);
    }

}
