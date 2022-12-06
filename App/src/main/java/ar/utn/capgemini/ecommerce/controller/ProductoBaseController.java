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
    public ResponseEntity<?> obtenerProductos() {
        return new ResponseEntity<>(productoBaseRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<?> crearProductoBase(@RequestBody @Valid ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!categoriaRepository.findById(productoBaseDTO.getCategoriaId()).isPresent())
            return new ResponseEntity<>("La categoria no existe", HttpStatus.BAD_REQUEST);

        Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
        ProductoBase productoBase = new ProductoBase();
        productoBase.setCategoria(categoria);
        productoBase.setDescripcion(productoBaseDTO.getDescripcion());
        productoBase.setPrecioBase(productoBaseDTO.getPrecioBase());
        productoBase.setProductoBaseUrl(productoBaseDTO.getProductoBaseUrl());
        productoBase.setTiempoDeFabricacion(productoBaseDTO.getTiempoDeFabricacion());
        productoBaseRepository.save(productoBase);

        return new ResponseEntity<>("Producto base creado con exito", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Integer id) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productoBaseRepository.findById(id).get(), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> modificarProductoBase(@PathVariable Integer id, @RequestBody ProductoBaseDTO productoBaseDTO) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        if (!categoriaRepository.findById(productoBaseDTO.getCategoriaId()).isPresent())
            return new ResponseEntity<>("Categoria no encontrada", HttpStatus.BAD_REQUEST);

        Categoria categoriaNueva = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
        ProductoBase productoBase = productoBaseRepository.findById(id).get();

        productoBase.setCategoria(categoriaNueva);
        productoBase.setPrecioBase(productoBaseDTO.getPrecioBase());
        productoBase.setProductoBaseUrl(productoBaseDTO.getProductoBaseUrl());
        productoBase.setDescripcion(productoBaseDTO.getDescripcion());
        productoBase.setTiempoDeFabricacion(productoBaseDTO.getTiempoDeFabricacion());
        productoBase.setFechaUltimaModificacion(LocalDateTime.now());
        return new ResponseEntity<>("Producto base modificado con exito", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> darProductoBaseDeBaja(@PathVariable Integer id) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(id).get();
        productoBase.setEstaActivo(false);
        productoBase.setFechaDeBaja(LocalDateTime.now());
        productoBase.setFechaUltimaModificacion(LocalDateTime.now());
        return new ResponseEntity<>("Producto base dado de baja con exito", HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/categoria")
    public ResponseEntity<?> obtenerCategoria(@PathVariable Integer id) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(id).get();
        return new ResponseEntity<>(productoBase.getCategoria(), HttpStatus.OK);

    }

    @Transactional
    @PatchMapping(path = "/{id}/categoria")
    public ResponseEntity<?> modificarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoria) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        if (!categoriaRepository.findById(categoria.getCategoria()).isPresent())
            return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(id).get();
        Categoria categoriaNueva = categoriaRepository.findById(categoria.getCategoria()).get();
        productoBase.setCategoria(categoriaNueva);
        productoBase.setFechaUltimaModificacion(LocalDateTime.now());
        return new ResponseEntity<>("Categoria modificada con exito", HttpStatus.OK);
    }


    /*
     * Para conservar la integridad referencial no puedo tener un productoBase sin categoria
     * es por esto que en este este controlador NO SE DEBE dar de baja una categoria. Para dar
     * de baja una categoria correctamente deberia crear un controlador para gestionarlas
     */

    @Transactional
    @DeleteMapping("/productosBase/{id}/categoria")
    public ResponseEntity<?> darCategoriaDeBaja(@PathVariable Integer id) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(id).get();
        productoBase.getCategoria().setEstaActivo(false);
        productoBase.getCategoria().setFechaDeBaja(LocalDateTime.now());
        productoBase.getCategoria().setFechaUltimaModificacion(LocalDateTime.now());
        return new ResponseEntity<>("Categoria dada de baja con exito", HttpStatus.OK);

    }

    @GetMapping(path = {"/{id}/posiblesPersonalizaciones"})
    public ResponseEntity<?> obtenerPosiblesPersonalizaciones(@PathVariable Integer id) {
        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(id).get();
        List<PosiblePersonalizacion> posiblesPersonalizaciones = productoBase.getPosiblesPersonalizaciones();
        return new ResponseEntity<>(posiblesPersonalizaciones, HttpStatus.OK);
    }

    /*
     * Un producto base tiene una lista de posibles personalizaciones. Para dar de baja una posible personalizacion
     * especifica, indico el producto y la personalizacion
     */
    @Transactional
    @DeleteMapping(path = {"/{productoBaseId}/posiblesPersonalizaciones/{posiblePersonalizacionId}"})
    public ResponseEntity<?> darPosiblePersonalizacionDeBaja(@PathVariable Integer productoBaseId, @PathVariable Integer posiblePersonalizacionId) {
        if (!productoBaseRepository.findById(productoBaseId).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        if (!posiblePersonalizacionRepository.findById(posiblePersonalizacionId).isPresent())
            return new ResponseEntity<>("Posible personalizacion no encontrada", HttpStatus.NOT_FOUND);

        PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(posiblePersonalizacionId).get();
        posiblePersonalizacion.setEstaActivo(false);
        posiblePersonalizacion.setFechaDeBaja(LocalDateTime.now());
        posiblePersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());
        return new ResponseEntity<>("Posible personalizacion dada de baja", HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/posiblesPersonalizaciones")
    public ResponseEntity<?> crearPosiblePersonalizacion(@PathVariable Integer id, @RequestBody PosiblePersonalizacionDTO posibleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!productoBaseRepository.findById(id).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        if (!tipoPersonalizacionRepository.findById(posibleDTO.getTipoPersonalizacion()).isPresent())
            return new ResponseEntity<>("Tipo de personalizacion no encontrado", HttpStatus.NOT_FOUND);

        if (!areaPersonalizacionRepository.findById(posibleDTO.getAreaPersonalizacion()).isPresent())
            return new ResponseEntity<>("Area de personalizacion no encontrada", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(id).get();
        AreaPersonalizacion area = areaPersonalizacionRepository.findById(posibleDTO.getAreaPersonalizacion()).get();
        TipoPersonalizacion tipo = tipoPersonalizacionRepository.findById(posibleDTO.getTipoPersonalizacion()).get();

        PosiblePersonalizacion posiblePersonalizacion = new PosiblePersonalizacion();
        posiblePersonalizacion.setAreaPersonalizacion(area);
        posiblePersonalizacion.setTipoPersonalizacion(tipo);
        posiblePersonalizacionRepository.save(posiblePersonalizacion);

        productoBase.agregarPosiblePersonalizacion(posiblePersonalizacion);
        productoBaseRepository.save(productoBase);

        return new ResponseEntity<>("Posible personalizacion creada", HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(path = "{productoBaseId}/posiblesPersonalizaciones/{posiblePersonalizacionId}")
    public ResponseEntity<?> modificarPosiblePersonalizacion(@PathVariable Integer productoBaseId, @PathVariable Integer posiblePersonalizacionId, @RequestBody PosiblePersonalizacionDTO posibleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        if (!productoBaseRepository.findById(productoBaseId).isPresent())
            return new ResponseEntity<>("Producto base no encontrado", HttpStatus.NOT_FOUND);

        if (!posiblePersonalizacionRepository.findById(posiblePersonalizacionId).isPresent())
            return new ResponseEntity<>("Posible personalizacion no encontrada", HttpStatus.NOT_FOUND);

        if (!tipoPersonalizacionRepository.findById(posibleDTO.getTipoPersonalizacion()).isPresent())
            return new ResponseEntity<>("Tipo de personalizacion no encontrado", HttpStatus.NOT_FOUND);

        if (!areaPersonalizacionRepository.findById(posibleDTO.getAreaPersonalizacion()).isPresent())
            return new ResponseEntity<>("Area de personalizacion no encontrada", HttpStatus.NOT_FOUND);

        ProductoBase productoBase = productoBaseRepository.findById(productoBaseId).get();
        AreaPersonalizacion area = areaPersonalizacionRepository.findById(posibleDTO.getAreaPersonalizacion()).get();
        TipoPersonalizacion tipo = tipoPersonalizacionRepository.findById(posibleDTO.getTipoPersonalizacion()).get();
        PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(posiblePersonalizacionId).get();

        posiblePersonalizacion.setAreaPersonalizacion(area);
        posiblePersonalizacion.setTipoPersonalizacion(tipo);
        posiblePersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());
        productoBase.agregarPosiblePersonalizacion(posiblePersonalizacion);
        productoBase.setFechaUltimaModificacion(LocalDateTime.now());

        return new ResponseEntity<>("Personalizacion actualizada", HttpStatus.OK);
    }
} // fin ProductoBaseController
