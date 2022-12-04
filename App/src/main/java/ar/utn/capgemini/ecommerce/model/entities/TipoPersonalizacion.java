package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tipoPersonalizacion")
public class TipoPersonalizacion extends EntidadPersistente {

    @Column(name = "estado")
    private boolean estaActivo;

    @Column(name = "descripcion")
    private String tipo;

    @PastOrPresent
    @Column(name = "fechaCreacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @PastOrPresent
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    public TipoPersonalizacion(String tipo, LocalDateTime fechaDeAlta) {
        this.tipo = tipo;
        this.estaActivo = true;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public TipoPersonalizacion() {
    }

}
