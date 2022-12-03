package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

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
    private LocalDate fechaDeAlta;

    @PastOrPresent
    private LocalDate fechaDeBaja;

    public TipoPersonalizacion(String tipo, LocalDate fechaDeAlta) {
        this.tipo = tipo;
        this.estaActivo = true;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaDeBaja = null;
    }

    public TipoPersonalizacion() {
    }

}
