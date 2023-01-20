package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.PersonalizacionConcretaDTO;
import ar.utn.capgemini.ecommerce.dto.ProductoPersonalizadoDTO;
import ar.utn.capgemini.ecommerce.model.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
public class ProductoPersonalizadoService {

    @Autowired
    private ProductoPersonalizadoRepository productoPersonalizadoRepository;
    @Autowired
    private ProductoBaseRepository productoBaseRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private PosiblePersonalizacionRepository posiblePersonalizacionRepository;
    @Autowired
    private PersonalizacionConcretaRepository personalizacionConcretaRepository;

    public ResponseEntity<?> listarProductosPersonalizados() {
        return new ResponseEntity<>(productoPersonalizadoRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerProductoPersonalizado(Integer id) {
        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).orElse(null);
        if (productoPersonalizado == null)
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productoPersonalizado, HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerPersonalizacionesConcretas(Integer id) {
        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).orElse(null);
        if (productoPersonalizado == null)
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productoPersonalizado.getPersonalizacionesConcretas(), HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerProductoBase(Integer id) {
        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).orElse(null);
        if (productoPersonalizado == null)
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productoPersonalizado.getProductoBase(), HttpStatus.OK);
    }

    public ResponseEntity<?> crearProductoPersonalizado(ProductoPersonalizadoDTO productoPersonalizadoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        ProductoBase productoBase = productoBaseRepository.findById(productoPersonalizadoDTO.getProductoBaseId()).orElse(null);
        if (productoBase == null)
            return new ResponseEntity<>("No se encontro el producto base", HttpStatus.NOT_FOUND);

        Vendedor vendedor = vendedorRepository.findById(productoPersonalizadoDTO.getVendedorId()).orElse(null);
        if (vendedor == null)
            return new ResponseEntity<>("No se encontro el vendedor", HttpStatus.NOT_FOUND);

        ProductoPersonalizado productoPersonalizado = new ProductoPersonalizado();
        productoPersonalizado.setProductoBase(productoBase);
        productoPersonalizado.setVendedor(vendedor);
        productoPersonalizado.setProductoPersonalizadoUrl(productoPersonalizadoDTO.getProductoPersonalizadoUrl());
        productoPersonalizadoRepository.save(productoPersonalizado);

        return new ResponseEntity<>(productoPersonalizado, HttpStatus.OK);
    }

    public ResponseEntity<?> agregarPersonalizacionConcreta(Integer id, PersonalizacionConcretaDTO personalizacionConcretaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).orElse(null);
        if (productoPersonalizado == null)
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(personalizacionConcretaDTO.getPosiblePersonalizacionId()).orElse(null);
        if (posiblePersonalizacion == null)
            return new ResponseEntity<>("No se encontro la posible personalizacion", HttpStatus.NOT_FOUND);

        PersonalizacionConcreta personalizacionConcreta = new PersonalizacionConcreta();
        personalizacionConcreta.setDetalle(personalizacionConcretaDTO.getDetalle());
        personalizacionConcreta.setPrecioPersonalizacionConcreta(personalizacionConcretaDTO.getPrecioPersonalizacion());
        personalizacionConcreta.setPosiblePersonalizacion(posiblePersonalizacion);
        personalizacionConcretaRepository.save(personalizacionConcreta);

        return new ResponseEntity<>("Se agrego la personalizacion concreta", HttpStatus.OK);
    }

    public ResponseEntity<?> darProductoPersonalizadoDeBaja(Integer id) {
        ProductoPersonalizado productoPersonalizado = productoPersonalizadoRepository.findById(id).orElse(null);
        if (productoPersonalizado == null)
            return new ResponseEntity<>("No se encontro el producto personalizado", HttpStatus.NOT_FOUND);

        productoPersonalizado.setEstaActivo(false);
        productoPersonalizado.setFechaDeBaja(LocalDateTime.now());
        productoPersonalizado.setFechaUltimaModificacion(LocalDateTime.now());
        productoPersonalizadoRepository.save(productoPersonalizado);

        return new ResponseEntity<>("Se dio de baja el producto personalizado", HttpStatus.OK);
    }
}
