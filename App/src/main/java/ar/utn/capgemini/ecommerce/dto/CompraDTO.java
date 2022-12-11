package ar.utn.capgemini.ecommerce.dto;

import ar.utn.capgemini.ecommerce.utils.PAGO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CompraDTO {

    /**
     * Recibo un clienteId opcional, esto es para buscarlo en la base de datos,
     * en caso de que exista. Si no existe ningun cliente con este id, creo
     * uno con los datos de la compra
     */
    private Integer clienteId;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccionCalle;
    private String direccionNumero;
    private String direccionPiso;
    private String direccionDepto;

    // info de la compra
    @NotNull
    private PAGO metodoDePago;

    // info del carrito
    @NotNull
    private List<PublicacionCarritoDTO> publicaciones;
}
