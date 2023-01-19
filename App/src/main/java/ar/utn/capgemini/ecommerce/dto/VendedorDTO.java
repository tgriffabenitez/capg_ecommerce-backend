package ar.utn.capgemini.ecommerce.dto;

import ar.utn.capgemini.ecommerce.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendedorDTO {
   private String nombreVendedor;
   private String apellidoVendedor;
   private String tiendaVendedor;
   private String emailVendedor;
   private List<PAGO> metodosDePago;
}
