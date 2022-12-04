package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.*;
import ar.utn.capgemini.ecommerce.model.entities.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(path = "/productosBase")
public class ProductoBaseController {

    @Autowired
    public ProductoBaseRepository productoBaseRepository;
    @Autowired
    public CategoriaRepository categoriaRepository;
    @Autowired
    public PosiblePersonalizacionRepository posiblePersonalizacionRepository;
    @Autowired
    public TipoPersonalizacionRepository tipoPersonalizacionRepository;
    @Autowired
    public AreaPersonalizacionRepository areaPersonalizacionRepository;

    @GetMapping(path = {"", "/"})
    public List<ProductoBase> obtenerProductos() {
        return productoBaseRepository.findAll();
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<String> crearProductoBase(@RequestBody @Valid ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
            ProductoBase productoBase = new ProductoBase(productoBaseDTO.getDescripcion(), productoBaseDTO.getPrecioBase(), productoBaseDTO.getTiempoDeFabricacion(), productoBaseDTO.getProductoBaseUrl(), categoria, LocalDateTime.now());

            productoBaseRepository.save(productoBase);
            return new ResponseEntity<>("Producto base creado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al crear el producto base", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductoBase> obtenerProductoPorId(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoEncontrado = productoBaseRepository.findById(id).get();
            return new ResponseEntity<>(productoEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PatchMapping(path = "/{id}")
    public ResponseEntity<String> modificarProductoBase(@PathVariable Integer id, @RequestBody ProductoBaseDTO productoBaseDTO) {
        if (productoBaseRepository.existsById(id)) {
            Categoria categoriaNueva = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            productoBase.setCategoria(categoriaNueva);
            productoBase.setPrecioBase(productoBaseDTO.getPrecioBase());
            productoBase.setProductoBaseUrl(productoBaseDTO.getProductoBaseUrl());
            productoBase.setDescripcion(productoBaseDTO.getDescripcion());
            productoBase.setTiempoDeFabricacion(productoBaseDTO.getTiempoDeFabricacion());
            productoBase.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>("Producto base modificado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> darProductoDeBaja(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            productoBase.setEstaActivo(false);
            productoBase.setFechaDeBaja(LocalDateTime.now());
            productoBase.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>("Producto base dado de baja con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}/categoria")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            return new ResponseEntity<>(productoBase.getCategoria(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PatchMapping(path = "/{id}/categoria")
    public ResponseEntity<String> modificarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoria) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            Categoria categoriaNueva = categoriaRepository.findById(categoria.getCategoria()).get();
            productoBase.setCategoria(categoriaNueva);
            productoBase.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>("Categoria modificada con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    /*
     * Para conservar la integridad referencial no puedo tener un productoBase sin categoria
     * es por esto que en este este controlador NO SE DEBE dar de baja una categoria. Para dar
     * de baja una categoria correctamente deberia crear un controlador para gestionarlas
     */

    /*
    @Transactional
    @DeleteMapping("/productosBase/{id}/categoria")
    public ResponseEntity<String> darCategoriaDeBaja(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            productoBase.getCategoria().setEstaActivo(false);
            productoBase.getCategoria().setFechaDeBaja(LocalDateTime.now());
            productoBase.getCategoria().setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>("Se dio de baja con exito la categoria", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    */

    @GetMapping(path = {"/{id}/posiblesPersonalizaciones"})
    public ResponseEntity<List<PosiblePersonalizacion>> obtenerPosiblesPersonalizaciones(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            List<PosiblePersonalizacion> posibles = productoBase.getPosiblesPersonalizaciones();
            return new ResponseEntity<>(posibles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * Un producto base tiene una lista de posibles personalizaciones. Para dar de baja una posible personalizacion
     * especifica, indico el producto y la personalizacion
     */
    @Transactional
    @DeleteMapping(path = {"/{productoBaseId}/posiblesPersonalizaciones/{posiblePersonalizacionId}"})
    public ResponseEntity<String> darPosiblePersonalizacionDeBaja(@PathVariable Integer productoBaseId, @PathVariable Integer posiblePersonalizacionId) {
        if (posiblePersonalizacionRepository.existsById(posiblePersonalizacionId) && productoBaseRepository.existsById(productoBaseId)) {
            PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(posiblePersonalizacionId).get();
            posiblePersonalizacion.setEstaActivo(false);
            posiblePersonalizacion.setFechaDeBaja(LocalDateTime.now());
            posiblePersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>("Personalizacion dada de baja", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el producto o la personalizacion", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/{id}/posiblesPersonalizaciones")
    public ResponseEntity<String> crearPosiblePersonalizacion(@PathVariable Integer id, @RequestBody PosiblePersonalizacionDTO posibleDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            boolean existeArea = areaPersonalizacionRepository.existsById(posibleDTO.getAreaPersonalizacion());
            boolean existeTipo = tipoPersonalizacionRepository.existsById(posibleDTO.getTipoPersonalizacion());
            AreaPersonalizacion areaPersonalizacion;
            TipoPersonalizacion tipoPersonalizacion;
            if (existeArea) {
                areaPersonalizacion = areaPersonalizacionRepository.findById(posibleDTO.getAreaPersonalizacion()).get();
            } else {
                return new ResponseEntity<>("No se encontro el area de personalizacion", HttpStatus.NOT_FOUND);
            }
            if (existeTipo) {
                tipoPersonalizacion = tipoPersonalizacionRepository.findById(posibleDTO.getTipoPersonalizacion()).get();
            } else {
                return new ResponseEntity<>("No se encontro el tipo de personalizacion", HttpStatus.NOT_FOUND);
            }
            PosiblePersonalizacion posiblePersonalizacion = new PosiblePersonalizacion(tipoPersonalizacion, areaPersonalizacion, LocalDateTime.now());
            posiblePersonalizacionRepository.save(posiblePersonalizacion);
            productoBase.agregarPosiblePersonalizacion(posiblePersonalizacion);
            productoBaseRepository.save(productoBase);
            return new ResponseEntity<>("Posible personalizacion agregada", HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PatchMapping(path = "{productoBaseId}/posiblesPersonalizaciones/{posiblePersonalizacionId}")
    public ResponseEntity<String> modificarPosiblePersonalizacion(@PathVariable Integer productoBaseId, @PathVariable Integer posiblePersonalizacionId, @RequestBody PosiblePersonalizacionDTO posibleDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && posiblePersonalizacionRepository.existsById(posiblePersonalizacionId) && productoBaseRepository.existsById(productoBaseId)) {

            ProductoBase productoBase = productoBaseRepository.findById(productoBaseId).get();
            AreaPersonalizacion areaPersonalizacion = areaPersonalizacionRepository.findById(posibleDTO.getAreaPersonalizacion()).get();
            TipoPersonalizacion tipoPersonalizacion = tipoPersonalizacionRepository.findById(posibleDTO.getTipoPersonalizacion()).get();
            PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(posiblePersonalizacionId).get();

            posiblePersonalizacion.setAreaPersonalizacion(areaPersonalizacion);
            areaPersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());

            posiblePersonalizacion.setTipoPersonalizacion(tipoPersonalizacion);
            tipoPersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());

            posiblePersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());
            productoBase.agregarPosiblePersonalizacion(posiblePersonalizacion);

            return new ResponseEntity<>("Personalizacion actualizada", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto o posible personalizacion no encontrados", HttpStatus.NOT_FOUND);
        }
    }
} // fin ProductoBaseController
