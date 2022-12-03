package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.CategoriaDTO;
import ar.utn.capgemini.ecommerce.model.dto.PosiblePersonalizacionDTO;
import ar.utn.capgemini.ecommerce.model.dto.ProductoBaseDTO;
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
import java.time.LocalDate;


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

    @PostMapping("/productosBase/")
    public ResponseEntity<ProductoBase> crearProductoBase(@RequestBody @Valid ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
            ProductoBase productoBase = new ProductoBase(productoBaseDTO.getDescripcion(), productoBaseDTO.getPrecioBase(), productoBaseDTO.getTiempoDeFabricacion(), productoBaseDTO.getProductoBaseUrl(), categoria, LocalDate.now());

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
            productoBase.setFechaDeBaja(LocalDate.now());
            return new ResponseEntity<>(HttpStatus.OK);
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
            categoria.setFechaDeBaja(LocalDate.now());
            categoriaRepository.save(categoria);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/productosBase/categorias")
    public ResponseEntity<Integer> crearCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (categoriaRepository.existsByCategoria(categoriaDTO.getCategoria())) {
                Categoria categoriaExistente = categoriaRepository.findByCategoria(categoriaDTO.getCategoria());
                return new ResponseEntity<>(categoriaExistente.getId(), HttpStatus.OK);
            } else {
                Categoria categoriaNueva = new Categoria(categoriaDTO.getCategoria(), LocalDate.now());
                categoriaRepository.save(categoriaNueva);
                return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping(path = {"/productosBase/posiblesPersonalizaciones/{id}"})
    public ResponseEntity<PosiblePersonalizacion> obtenerPosiblesPersonalizacionesPorId(@PathVariable Integer id) {
        if (posiblePersonalizacionRepository.existsById(id)) {
            PosiblePersonalizacion posiblePersonalizacionEncontrada = posiblePersonalizacionRepository.findById(id).get();
            return new ResponseEntity<>(posiblePersonalizacionEncontrada, HttpStatus.OK);
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
            posiblePersonalizacionRepository.save(posiblePersonalizacion);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /* ===============================================================================================================*/
    @GetMapping(path = "/productosBase/tiposPersonalizaciones")
    public Page<TipoPersonalizacion> obtenerTiposPersonalizaciones(Pageable pagina) {
        return tipoPersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/tiposPersonalizaciones/{id}"})
    public ResponseEntity<TipoPersonalizacion> obtenerTipoPersonalizacionPorId(@PathVariable Integer id) {
        if (tipoPersonalizacionRepository.existsById(id)) {
            TipoPersonalizacion tipoPersonalizacion = tipoPersonalizacionRepository.findById(id).get();
            return new ResponseEntity<>(tipoPersonalizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/tiposPersonalizaciones/{id}"})
    public ResponseEntity<TipoPersonalizacion> darTipoPersonalizacionDeBaja(@PathVariable Integer id) {
        if (tipoPersonalizacionRepository.existsById(id)) {
            TipoPersonalizacion tipoPersonalizacion = tipoPersonalizacionRepository.findById(id).get();
            tipoPersonalizacion.setEstaActivo(false);
            tipoPersonalizacionRepository.save(tipoPersonalizacion);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* ===============================================================================================================*/
    @GetMapping(path = "/productosBase/areasPersonalizaciones")
    public Page<AreaPersonalizacion> obtenerAreasPersonalizaciones(Pageable pagina) {
        return areaPersonalizacionRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/areasPersonalizaciones/{id}"})
    public ResponseEntity<AreaPersonalizacion> obtenerAreaPersonalizacionPorId(@PathVariable Integer id) {
        if (areaPersonalizacionRepository.existsById(id)) {
            AreaPersonalizacion areaPersonalizacion = areaPersonalizacionRepository.findById(id).get();
            return new ResponseEntity<>(areaPersonalizacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/areasPersonalizaciones/{id}"})
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

    @PostMapping("/productosBase/areasPersonalizaciones/")
    public ResponseEntity<AreaPersonalizacion> agregarAreaPersonalizacion(@RequestBody @Valid PosiblePersonalizacionDTO posiblePersonalizacionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            AreaPersonalizacion area = areaPersonalizacionRepository.findById(posiblePersonalizacionDTO.getAreaPersonalizacionId()).get();
            areaPersonalizacionRepository.save(area);
            return new ResponseEntity<>(area, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
