package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.*;
import ar.utn.capgemini.ecommerce.model.entities.*;
import ar.utn.capgemini.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping
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

    /* ===============================================================================================================*/
    @GetMapping("/productosBase/")
    public Page<ProductoBase> obtenerProductos(Pageable pagina) {
        return productoBaseRepository.findAll(pagina);
    }

    @GetMapping("/productosBase/{id}")
    public ResponseEntity<ProductoBase> obtenerProductoPorId(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoEncontrado = productoBaseRepository.findById(id).get();
            return new ResponseEntity<>(productoEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productosBase")
    public ResponseEntity<ProductoBase> crearProductoBase(@RequestBody @Valid ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
            ProductoBase productoBase = new ProductoBase(productoBaseDTO.getDescripcion(), productoBaseDTO.getPrecioBase(), productoBaseDTO.getTiempoDeFabricacion(), productoBaseDTO.getProductoBaseUrl(), categoria, LocalDateTime.now());

            productoBaseRepository.save(productoBase);
            return new ResponseEntity<>(productoBase, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PatchMapping("/productosBase/{id}")
    public ResponseEntity<ProductoBase> modificarProductoBase(@PathVariable Integer id, @RequestBody ProductoBaseDTO productoBaseDTO) {
        if (productoBaseRepository.existsById(id)) {
            Categoria categoriaNueva = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            productoBase.setCategoria(categoriaNueva);
            productoBase.setPrecioBase(productoBaseDTO.getPrecioBase());
            productoBase.setProductoBaseUrl(productoBaseDTO.getProductoBaseUrl());
            productoBase.setDescripcion(productoBaseDTO.getDescripcion());
            productoBase.setTiempoDeFabricacion(productoBaseDTO.getTiempoDeFabricacion());
            productoBase.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>(productoBase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/productosBase/{id}")
    public ResponseEntity<ProductoBase> darProductoDeBaja(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            productoBase.setEstaActivo(false);
            productoBase.setFechaDeBaja(LocalDateTime.now());
            return new ResponseEntity<>(productoBase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* =================================================================================*/
    @GetMapping("/productosBase/categorias")
    public Page<Categoria> obtenerCategorias(Pageable pagina) {
        return categoriaRepository.findAll(pagina);
    }

    @GetMapping("/productosBase/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Integer id) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoriaEncontrada = categoriaRepository.findById(id).get();
            return new ResponseEntity<>(categoriaEncontrada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("/productosBase/categorias/{id}")
    public ResponseEntity<Categoria> darCategoriaDeBaja(@PathVariable Integer id) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoria = categoriaRepository.findById(id).get();
            categoria.setEstaActivo(false);
            categoria.setFechaDeBaja(LocalDateTime.now());
            categoria.setFechaUltimaModificacion(LocalDateTime.now());
            categoriaRepository.save(categoria);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productosBase/categorias")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (categoriaRepository.existsByCategoria(categoriaDTO.getCategoria())) {
                Categoria categoriaExistente = categoriaRepository.findByCategoria(categoriaDTO.getCategoria());
                return new ResponseEntity<>(categoriaExistente, HttpStatus.OK);
            } else {
                Categoria categoriaNueva = new Categoria(categoriaDTO.getCategoria(), LocalDateTime.now());
                categoriaRepository.save(categoriaNueva);
                return new ResponseEntity<>(categoriaNueva, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PatchMapping("/productosBase/categorias/{id}")
    public ResponseEntity<Categoria> modificarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoria = categoriaRepository.findById(id).get();
            categoria.setCategoria(categoriaDTO.getCategoria());
            categoria.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /* ===============================================================================================================*/
    @GetMapping(path = "/productosBase/posiblesPersonalizaciones")
    public Page<PosiblePersonalizacion> obtenerPosiblesPersonalizaciones(Pageable pagina) {
        return posiblePersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/{id}/posiblesPersonalizaciones"})
    public ResponseEntity<List<PosiblePersonalizacion>> obtenerPosiblesPersonalizacionesPorId(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            List<PosiblePersonalizacion> posibles = productoBase.getPosiblesPersonalizaciones();
            return new ResponseEntity<>(posibles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/posiblesPersonalizaciones/{id}"})
    public ResponseEntity<PosiblePersonalizacion> darPosiblePersonalizacionDeBaja(@PathVariable Integer id) {
        if (posiblePersonalizacionRepository.existsById(id)) {
            PosiblePersonalizacion posiblePersonalizacion = posiblePersonalizacionRepository.findById(id).get();
            posiblePersonalizacion.setEstaActivo(false);
            posiblePersonalizacion.setFechaDeBaja(LocalDateTime.now());
            posiblePersonalizacion.setFechaUltimaModificacion(LocalDateTime.now());
            return new ResponseEntity<>(posiblePersonalizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productosBase/{id}/posiblesPersonalizaciones")
    public ResponseEntity<PosiblePersonalizacion> crearPosiblePersonalizacion(@PathVariable Integer id, @RequestBody PosiblePersonalizacionDTO posibleDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            boolean existeArea = areaPersonalizacionRepository.existsByArea(posibleDTO.getAreaPersonalizacion());
            boolean existeTipo = tipoPersonalizacionRepository.existsByTipo(posibleDTO.getTipoPersonalizacion());

            if (existeTipo && existeArea) {
                TipoPersonalizacion tipoExiste = tipoPersonalizacionRepository.findByTipo(posibleDTO.getTipoPersonalizacion());
                AreaPersonalizacion areaExiste = areaPersonalizacionRepository.findByArea(posibleDTO.getAreaPersonalizacion());
                PosiblePersonalizacion posibleExiste = posiblePersonalizacionRepository.findByTipoPersonalizacionAndAreaPersonalizacion(tipoExiste, areaExiste);
                productoBase.agregarPosiblePersonalizacion(posibleExiste);
                return new ResponseEntity<>(posibleExiste, HttpStatus.OK);

            } else {
                TipoPersonalizacion tipoNuevo = new TipoPersonalizacion(posibleDTO.getTipoPersonalizacion(), LocalDateTime.now());
                tipoNuevo.setFechaDeAlta(LocalDateTime.now());
                tipoPersonalizacionRepository.save(tipoNuevo);
                AreaPersonalizacion areaNueva = new AreaPersonalizacion(posibleDTO.getAreaPersonalizacion(), LocalDateTime.now());
                areaNueva.setFechaDeAlta(LocalDateTime.now());
                areaPersonalizacionRepository.save(areaNueva);
                PosiblePersonalizacion posibleNuevo = new PosiblePersonalizacion(tipoNuevo, areaNueva, LocalDateTime.now());
                posiblePersonalizacionRepository.save(posibleNuevo);
                productoBase.agregarPosiblePersonalizacion(posibleNuevo);
                productoBaseRepository.save(productoBase);
                return new ResponseEntity<>(posibleNuevo, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /* ===============================================================================================================*/
    @GetMapping(path = "/productosBase/posiblesPersonalizaciones/tipo")
    public Page<TipoPersonalizacion> obtenerTiposPersonalizaciones(Pageable pagina) {
        return tipoPersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/posiblesPersonalizaciones/tipo/{id}"})
    public ResponseEntity<TipoPersonalizacion> obtenerTipoPersonalizacionPorId(@PathVariable Integer id) {
        if (tipoPersonalizacionRepository.existsById(id)) {
            TipoPersonalizacion tipoPersonalizacion = tipoPersonalizacionRepository.findById(id).get();
            return new ResponseEntity<>(tipoPersonalizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/posiblesPersonalizaciones/tipo/{id}"})
    public ResponseEntity<TipoPersonalizacion> darTipoPersonalizacionDeBaja(@PathVariable Integer id) {
        if (tipoPersonalizacionRepository.existsById(id)) {
            TipoPersonalizacion tipoPersonalizacion = tipoPersonalizacionRepository.findById(id).get();
            tipoPersonalizacion.setEstaActivo(false);
            tipoPersonalizacionRepository.save(tipoPersonalizacion);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/productosBase/posiblesPersonalizaciones/tipo")
    public ResponseEntity<TipoPersonalizacion> crearTipoPersonalizacion(@RequestBody @Valid TipoPersonalizacionDTO tipoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {

            if (tipoPersonalizacionRepository.existsByTipo(tipoDTO.getTipoPersonalizacion())) {
                TipoPersonalizacion tipoExistente = tipoPersonalizacionRepository.findByTipo(tipoDTO.getTipoPersonalizacion());
                return new ResponseEntity<>(tipoExistente, HttpStatus.OK);
            } else {
                TipoPersonalizacion tipoNuevo = new TipoPersonalizacion(tipoDTO.getTipoPersonalizacion(), LocalDateTime.now());
                tipoNuevo.setFechaDeAlta(LocalDateTime.now());
                tipoPersonalizacionRepository.save(tipoNuevo);
                return new ResponseEntity<>(tipoNuevo, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /* ===============================================================================================================*/
    @GetMapping(path = "/productosBase/posiblesPersonalizaciones/area")
    public Page<AreaPersonalizacion> obtenerAreasPersonalizaciones(Pageable pagina) {
        return areaPersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/posiblesPersonalizaciones/area/{id}"})
    public ResponseEntity<AreaPersonalizacion> obtenerAreaPersonalizacionPorId(@PathVariable Integer id) {
        if (areaPersonalizacionRepository.existsById(id)) {
            AreaPersonalizacion areaPersonalizacion = areaPersonalizacionRepository.findById(id).get();
            return new ResponseEntity<>(areaPersonalizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/posiblesPersonalizaciones/area/{id}"})
    public ResponseEntity<AreaPersonalizacion> darAreaPersonalizacionDeBaja(@PathVariable Integer id) {
        if (areaPersonalizacionRepository.existsById(id)) {
            AreaPersonalizacion areaPersonalizacion = areaPersonalizacionRepository.findById(id).get();
            areaPersonalizacion.setEstaActivo(false);
            areaPersonalizacionRepository.save(areaPersonalizacion);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productosBase/posiblesPersonalizaciones/area")
    public ResponseEntity<AreaPersonalizacion> crearAreaPersonalizacion(@RequestBody @Valid AreaPersonalizacionDTO areaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (areaPersonalizacionRepository.existsByArea(areaDTO.getAreaPersonalizacion())) {
                AreaPersonalizacion areaExistente = areaPersonalizacionRepository.findByArea(areaDTO.getAreaPersonalizacion());
                return new ResponseEntity<>(areaExistente, HttpStatus.OK);
            } else {
                AreaPersonalizacion areaNueva = new AreaPersonalizacion(areaDTO.getAreaPersonalizacion(), LocalDateTime.now());
                areaNueva.setFechaDeAlta(LocalDateTime.now());
                areaPersonalizacionRepository.save(areaNueva);
                return new ResponseEntity<>(areaNueva, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

} // fin ProductoBaseController
