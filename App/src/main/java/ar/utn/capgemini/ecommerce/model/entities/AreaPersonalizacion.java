package ar.utn.capgemini.ecommerce.model.entities;

import ar.utn.capgemini.ecommerce.model.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "areaPersonalizacion")
public class AreaPersonalizacion extends EntidadPersistente {

    @NotBlank
    @NotEmpty
    @Column(name = "area")
    private String area;

    public AreaPersonalizacion(String area) {
        this.area = area;
    }

    public AreaPersonalizacion() {
    }

}
