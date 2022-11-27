package ar.utn.capgemini.ecommerce.model.persist;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}