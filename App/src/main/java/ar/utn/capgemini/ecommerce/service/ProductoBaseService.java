package ar.utn.capgemini.ecommerce.service;

import ar.utn.capgemini.ecommerce.dto.PosiblePersonalizacionDTO;
import ar.utn.capgemini.ecommerce.dto.PosiblesPersonalizacionesDTO;
import ar.utn.capgemini.ecommerce.dto.ProductoBaseDTO;
import ar.utn.capgemini.ecommerce.model.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Service
public class ProductoBaseService {

    @Autowired
    private ProductoBaseRepository productoBaseRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PosiblePersonalizacionRepository posiblePersonalizacionRepository;
    @Autowired
    private TipoPersonalizacionRepository tipoPersonalizacionRepository;
    @Autowired
    private AreaPersonalizacionRepository areaPersonalizacionRepository;

    public ResponseEntity<?> listarProductosBase() {
        return new ResponseEntity<>(productoBaseRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> obtenerProductoBase(Integer id) {
        ProductoBase productoBase = productoBaseRepository.findById(id).orElse(null);
        if (productoBase == null)
            return new ResponseEntity<>("No existe el producto base", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productoBase, HttpStatus.OK);
    }

    public ResponseEntity<?> crearProductoBase(ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).orElse(null);
        if (categoria == null)
            return new ResponseEntity<>("No existe la categoria", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = new ProductoBase();
        productoBase.setDescripcion(productoBaseDTO.getDescripcion());
        productoBase.setPrecioBase(productoBaseDTO.getPrecioBase());
        productoBase.setTiempoDeFabricacion(productoBaseDTO.getTiempoDeFabricacion());
        productoBase.setProductoBaseUrl(productoBaseDTO.getProductoBaseUrl());
        productoBase.setCategoria(categoria);
        productoBaseRepository.save(productoBase);

        return new ResponseEntity<>("Producto base creado", HttpStatus.OK);
    }

    public ResponseEntity<?> agregarPosiblePersonalizacion(Integer id, PosiblesPersonalizacionesDTO posiblesPersonalizacionesDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        ProductoBase productoBase = productoBaseRepository.findById(id).orElse(null);
        if (productoBase == null)
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        for (PosiblePersonalizacionDTO posiblePersonalizacionDTO : posiblesPersonalizacionesDTO.getPosiblesPersonalizaciones()) {
            TipoPersonalizacion tipoPersonalizacion = tipoPersonalizacionRepository.findById(posiblePersonalizacionDTO.getTipoPersonalizacion()).orElse(null);
            AreaPersonalizacion areaPersonalizacion = areaPersonalizacionRepository.findById(posiblePersonalizacionDTO.getAreaPersonalizacion()).orElse(null);

            if ((tipoPersonalizacion == null) || (areaPersonalizacion == null))
                return new ResponseEntity<>("No existe el el tipo de personalizacion o el area de personalizacion", HttpStatus.NOT_FOUND);

            PosiblePersonalizacion posiblePersonalizacion = new PosiblePersonalizacion(tipoPersonalizacion, areaPersonalizacion);
            posiblePersonalizacionRepository.save(posiblePersonalizacion);

            productoBase.agregarPosiblePersonalizacion(posiblePersonalizacion);
        }
        productoBaseRepository.save(productoBase);

        return new ResponseEntity<>("Posible personalizacion agregada", HttpStatus.OK);
    }

    public ResponseEntity<?> modificarProductoBase(Integer id, ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        ProductoBase productoBase = productoBaseRepository.findById(id).orElse(null);
        if (productoBase == null)
            return new ResponseEntity<>("No se encontro el producto base", HttpStatus.NOT_FOUND);

        if (productoBaseDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).orElse(null);
            if (categoria == null)
                return new ResponseEntity<>("No existe la categoria", HttpStatus.NOT_FOUND);

            productoBase.setCategoria(categoria);
        }

        if (productoBaseDTO.getDescripcion() != null)
            productoBase.setDescripcion(productoBaseDTO.getDescripcion());

        if (productoBaseDTO.getPrecioBase() != null)
            productoBase.setPrecioBase(productoBaseDTO.getPrecioBase());

        if (productoBaseDTO.getTiempoDeFabricacion() != null)
            productoBase.setTiempoDeFabricacion(productoBaseDTO.getTiempoDeFabricacion());

        if (productoBaseDTO.getProductoBaseUrl() != null)
            productoBase.setProductoBaseUrl(productoBaseDTO.getProductoBaseUrl());

        productoBaseRepository.save(productoBase);

        return new ResponseEntity<>("Producto base creado", HttpStatus.OK);
    }

    public ResponseEntity<?> darDeBaja(Integer id) {
        ProductoBase productoBase = productoBaseRepository.findById(id).orElse(null);
        if (productoBase == null)
            return new ResponseEntity<>("No se encontro el producto base", HttpStatus.NOT_FOUND);

        productoBase.setFechaDeBaja(LocalDateTime.now());
        productoBase.setFechaUltimaModificacion(LocalDateTime.now());
        productoBase.setEstaActivo(false);
        productoBaseRepository.save(productoBase);

        return new ResponseEntity<>("Producto base dado de baja", HttpStatus.OK);
    }

}
