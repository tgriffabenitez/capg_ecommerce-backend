package ar.utn.capgemini.ecommerce.model.entities;

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
@Table(name = "vendedor")
public class Vendedor extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "nombre")
    private String nombreVendedor;

    @NotBlank
    @NotEmpty
    @Column(name = "apellido")
    private String apellidoVendedor;

    @NotBlank
    @NotEmpty
    @Column(name = "tienda")
    private String tienda;

    @NotBlank
    @NotEmpty
    @Email
    private String emailVendedor;

    @ManyToMany
    @Column(name = "metodosDePago")
    private List<MetodoDePago> metodosDePago;

    public Vendedor() {
        this.metodosDePago = new ArrayList<>();
    }

}
