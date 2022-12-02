package ar.utn.capgemini.ecommerce.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "tipoPersonalizacion")
public class TipoPersonalizacion extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "descripcion")
    private String tipo;

    public TipoPersonalizacion(String tipo) {
        this.tipo = tipo;
    }

    public TipoPersonalizacion() {
    }

}
