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
@Table(name = "vendedor")
public class Vendedor extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @NotEmpty
    @Column(name = "apellido")
    private String apellido;

    @NotBlank
    @NotEmpty
    @Column(name = "tienda")
    private String tienda;

    @NotBlank
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @ManyToMany
    @Column(name = "metodosDePago")
    private List<MetodoDePago> metodosDePago;

    public Vendedor() {
        this.metodosDePago = new ArrayList<>();
    }

}
