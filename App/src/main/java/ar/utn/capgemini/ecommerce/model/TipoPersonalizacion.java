package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaCreacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeAlta;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaDeBaja", columnDefinition = "DATETIME")
    private LocalDateTime fechaDeBaja;

    @JsonIgnore
    @PastOrPresent
    @Column(name = "fechaUltimaModificacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaUltimaModificacion;

    public TipoPersonalizacion(String tipo) {
        this.tipo = tipo;
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

    public TipoPersonalizacion() {
        this.estaActivo = true;
        this.fechaDeAlta = LocalDateTime.now();
        this.fechaDeBaja = null;
        this.fechaUltimaModificacion = null;
    }

}
