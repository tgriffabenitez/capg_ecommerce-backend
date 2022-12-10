package ar.utn.capgemini.ecommerce.model;

import ar.utn.capgemini.ecommerce.utils.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "compra")
public class Compra extends EntidadPersistente {


}
