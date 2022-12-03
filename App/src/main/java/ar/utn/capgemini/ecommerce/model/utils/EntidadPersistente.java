package ar.utn.capgemini.ecommerce.model.utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public EntidadPersistente() {
    }
}