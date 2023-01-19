package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
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

    @Column(name = "fechaDeAlta", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @Column(name = "fechaDeModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeModificacion;

    @Column(name = "estado")
    private boolean estado;

    public Vendedor(String nombreVendedor, String apellidoVendedor, String tienda, String emailVendedor) {
        this.metodosDePago = new ArrayList<>();
        this.nombreVendedor = nombreVendedor;
        this.apellidoVendedor = apellidoVendedor;
        this.tienda = tienda;
        this.emailVendedor = emailVendedor;
        this.fechaDeAlta = LocalDateTime.now();
        this.estado = true;
    }

    public Vendedor() {
        this.metodosDePago = new ArrayList<>();
        this.fechaDeAlta = LocalDateTime.now();
        this.estado = true;
    }

    public void agregarMetodoDePago(MetodoDePago metodoDePago) {
        this.metodosDePago.add(metodoDePago);
    }

}
