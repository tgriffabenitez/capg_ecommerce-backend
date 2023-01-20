package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import ar.utn.capgemini.ecommerce.utils.PAGO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "formaDePago")
    private PAGO metodoDePago;

    @PositiveOrZero
    @Column(name = "precioTotal")
    private Double precioTotal;

    @PastOrPresent
    @Column(name = "fechaDeCompra", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeCompra;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "CompraId", referencedColumnName = "id")
    private List<PublicacionPorCarrito> publicacionesPorCarrito = new ArrayList<>();

    public Compra() {
        this.fechaDeCompra = LocalDateTime.now();
    }

    public void agregarPublicacion(PublicacionPorCarrito publicacionPorCarrito){
        this.publicacionesPorCarrito.add(publicacionPorCarrito);
    }
}
