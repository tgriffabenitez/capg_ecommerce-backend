package ar.utn.capgemini.ecommerce.model.entities;

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
    private String contraseniaCliente;

    @OneToMany
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private List<Compra> compras;

    public Cliente() {
        this.compras = new ArrayList<>();
    }

}
