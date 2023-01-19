
package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.VendedorDTO;
import ar.utn.capgemini.ecommerce.model.MetodoDePago;
import ar.utn.capgemini.ecommerce.model.Vendedor;
import ar.utn.capgemini.ecommerce.repository.MetodoDePagoRepository;
import ar.utn.capgemini.ecommerce.repository.VendedorRepository;
import ar.utn.capgemini.ecommerce.utils.PAGO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;


@Service
public class VendedorService {
    @Autowired private VendedorRepository vendedorRepository;
    @Autowired private MetodoDePagoRepository metodoDePagoRepository;

    public ResponseEntity<?> listarVendedores() {
        return new ResponseEntity<>(vendedorRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerVendedorPorId(Integer id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        return (vendedor == null)? new ResponseEntity<>("Vendedor no encontrado", HttpStatus.NOT_FOUND) : new ResponseEntity<>(vendedor, HttpStatus.OK);
    }

    public ResponseEntity<?> agregarVendedor(VendedorDTO vendedorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        boolean tiendaRegistrada = vendedorRepository.existsByTienda(vendedorDTO.getTiendaVendedor());

        if (tiendaRegistrada)
            return new ResponseEntity<>("La tienda ya esta registrada", HttpStatus.CONFLICT);

        Vendedor vendedor = new Vendedor();
        vendedor.setNombreVendedor(vendedorDTO.getNombreVendedor());
        vendedor.setApellidoVendedor(vendedorDTO.getApellidoVendedor());
        vendedor.setTienda(vendedorDTO.getTiendaVendedor());
        vendedor.setEmailVendedor(vendedorDTO.getEmailVendedor());

        for (PAGO pago : vendedorDTO.getMetodosDePago()){
            MetodoDePago metodoDePago = metodoDePagoRepository.findByFormaDePago(pago);
            if (metodoDePago == null)
                return new ResponseEntity<>("Metodo de pago incorrecto: " + metodoDePago, HttpStatus.BAD_REQUEST);
            vendedor.agregarMetodoDePago(metodoDePago);
        }

        vendedorRepository.save(vendedor);
        return new ResponseEntity<>("Vendedor agregado", HttpStatus.CREATED);
    }

    public ResponseEntity<?> darVendedorDeBaja(Integer id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        if (vendedor == null)
            return new ResponseEntity<>("Vendedor no encontrado", HttpStatus.NOT_FOUND);

        vendedor.setFechaDeBaja(LocalDateTime.now());
        vendedor.setFechaDeModificacion(LocalDateTime.now());
        vendedor.setEstado(false);
        vendedorRepository.save(vendedor);
        return new ResponseEntity<>("Vendedor dado de baja", HttpStatus.OK);
    }

    public ResponseEntity<?> modificarVendedor(Integer id, VendedorDTO vendedorDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        if (vendedor == null)
            return new ResponseEntity<>("Vendedor no encontrado", HttpStatus.NOT_FOUND);

        if (vendedorDTO.getNombreVendedor() != null)
            vendedor.setNombreVendedor(vendedorDTO.getNombreVendedor());

        if (vendedorDTO.getApellidoVendedor() != null)
            vendedor.setApellidoVendedor(vendedorDTO.getApellidoVendedor());

        if (vendedorDTO.getTiendaVendedor() != null) {
            boolean tiendaRegistrada = vendedorRepository.existsByTienda(vendedorDTO.getTiendaVendedor());
            if (tiendaRegistrada)
                return new ResponseEntity<>("La tienda ya esta registrada", HttpStatus.CONFLICT);

            vendedor.setTienda(vendedorDTO.getTiendaVendedor());
        }

        if (vendedorDTO.getEmailVendedor() != null)
            vendedor.setEmailVendedor(vendedorDTO.getEmailVendedor());

        if (vendedorDTO.getMetodosDePago() != null) {
            for (PAGO pago : vendedorDTO.getMetodosDePago()) {
                MetodoDePago metodoDePago = metodoDePagoRepository.findByFormaDePago(pago);
                if (metodoDePago == null)
                    return new ResponseEntity<>("Metodo de pago incorrecto: " + metodoDePago, HttpStatus.BAD_REQUEST);

                if (!vendedor.getMetodosDePago().contains(metodoDePago)) {
                    vendedor.agregarMetodoDePago(metodoDePago);
                }
            }
        }

        vendedorRepository.save(vendedor);
        return new ResponseEntity<>("Vendedor actualizado", HttpStatus.OK);
    }
}
