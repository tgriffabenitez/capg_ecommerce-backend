package ar.utn.capgemini.ecommerce.controller;

import ar.utn.capgemini.ecommerce.model.dto.ProductoBaseDTO;
import ar.utn.capgemini.ecommerce.model.entities.Categoria;
import ar.utn.capgemini.ecommerce.model.entities.ProductoBase;
import ar.utn.capgemini.ecommerce.repository.CategoriaRepository;
import ar.utn.capgemini.ecommerce.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping
public class ProductoBaseController {

    @Autowired
    public ProductoBaseRepository productoBaseRepository;
    @Autowired
    public CategoriaRepository categoriaRepository;

    @GetMapping("/productosBase")
    public Page<ProductoBase> obtenerProductos(Pageable pagina) {
        return productoBaseRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/{id}"})
    public ResponseEntity<ProductoBase> obtenerProductoPorId(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoEncontrado = productoBaseRepository.findById(id).get();
            return new ResponseEntity<>(productoEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/{id}"})
    public ResponseEntity<ProductoBase> darProductoDeBaja(@PathVariable Integer id) {
        if (productoBaseRepository.existsById(id)) {
            ProductoBase productoBase = productoBaseRepository.findById(id).get();
            productoBase.setEstaActivo(false);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = {"/productosBase"})
    public ResponseEntity<ProductoBase> crearProductoBase(@RequestBody @Valid ProductoBaseDTO productoBaseDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Categoria categoria = categoriaRepository.findById(productoBaseDTO.getCategoriaId()).get();
            ProductoBase productoBase = new ProductoBase(productoBaseDTO.getDescripcion(), productoBaseDTO.getPrecioBase(),
                    productoBaseDTO.getTiempoDeFabricacion(), productoBaseDTO.getProductoBaseurl(), categoria, true);

            productoBaseRepository.save(productoBase);
            return new ResponseEntity<>(productoBase, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/productosBase/categorias")
    public Page<Categoria> obtenerCategorias(Pageable pagina) {
        return categoriaRepository.findAll(pagina);
    }

    @GetMapping(path = {"/productosBase/categorias/{id}"})
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Integer id) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoriaEncontrada = categoriaRepository.findById(id).get();
            return new ResponseEntity<>(categoriaEncontrada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping(path = {"/productosBase/categorias/{id}"})
    public ResponseEntity<Categoria> darCategoriaDeBaja(@PathVariable Integer id) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoria = categoriaRepository.findById(id).get();
            categoria.setEstaActivo(false);
            categoriaRepository.save(categoria);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






































/*
    1) post producto/id/posible
    2) post posible
    * /{productoBaseId}/posible
    *
*/

}
