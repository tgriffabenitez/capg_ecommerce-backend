package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.model.Compra;
import ar.utn.capgemini.ecommerce.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public ResponseEntity<?> obtenerCompra(Integer id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra == null)
            return new ResponseEntity<>("No se encontro la compra", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(compra, HttpStatus.OK);
    }
}
