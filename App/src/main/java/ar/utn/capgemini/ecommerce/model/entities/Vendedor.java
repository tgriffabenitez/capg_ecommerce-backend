package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.persist.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "tienda")
    private String tienda;

    @ManyToMany
    @Column(name = "metodosDePago")
    private List<MetodoDePago> metodosDePago;

    @OneToMany(mappedBy = "vendedor")
    @JsonBackReference
    private List<PersonalizacionConcreta> personalizacionesConcretas;

    @OneToMany(mappedBy = "vendedor")
    @JsonBackReference
    private List<Publicacion> publicaciones;

    public Vendedor() {
        this.metodosDePago = new ArrayList<>();
        this.personalizacionesConcretas = new ArrayList<>();
    }

    public void agregarPersonalizacionConcreta(PersonalizacionConcreta personalizacionConcreta){
        this.personalizacionesConcretas.add(personalizacionConcreta);
        personalizacionConcreta.setVendedor(this);
    }

    public void agregarPublicacion(Publicacion publicacion){
        this.publicaciones.add(publicacion);
        publicacion.setVendedor(this);
    }
}
