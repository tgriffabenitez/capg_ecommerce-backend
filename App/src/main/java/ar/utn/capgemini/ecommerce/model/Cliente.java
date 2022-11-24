package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadPersistente {

    private String nombreCliente;

    private String apellidoCliente;

    private String emailCliente;

    private String contrasenia;

    private List<Compra> compras;

    private Carrito carrito;

    public Cliente() {
        this.compras = new ArrayList<>();
    }
}
