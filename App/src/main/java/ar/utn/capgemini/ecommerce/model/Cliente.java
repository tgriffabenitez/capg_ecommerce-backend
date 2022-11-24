package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadPersistente {

    @Column(name = "nombreCliente")
    private String nombreCliente;

    @Column(name = "apellidoCliente")
    private String apellidoCliente;

    @Column(name = "emailCliente")
    private String emailCliente;

    @Column(name = "contrasenia")
    private String contrasenia;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @OneToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;

    public Cliente() {
        this.compras = new ArrayList<>();
    }

    public void agregarCompra(Compra compra){
        this.compras.add(compra);
        compra.setCliente(this);
    }
}
