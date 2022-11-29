package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @OneToMany
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private List<Compra> compras;

    public Cliente() {
        this.compras = new ArrayList<>();
    }

}
