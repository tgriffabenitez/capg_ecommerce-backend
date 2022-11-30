package ar.utn.capgemini.ecommerce.controladores;

import ar.utn.capgemini.ecommerce.modelo.entidades.productos.Categoria;
import ar.utn.capgemini.ecommerce.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "categorias")
public class CategoriaController {

    @Autowired
    public CategoriaRepository categoriaRepository;

    @GetMapping(path = {"", "/"})
    public Page<Categoria> obtenerCategoria(Pageable pagina){
        return categoriaRepository.findAll(pagina);
    }

    @GetMapping(path = {"/{categoriaId}"})
    public Optional<Categoria> obtenerCategoriaId(@PathVariable("categoriaId") Integer id){
        return categoriaRepository.findById(id);
    }

    @PostMapping(path = {"", "/"})
    public Categoria agregarCategoria(@RequestBody @Validated Categoria categoria){
        if(categoriaRepository.existsByCategoria(categoria.getCategoria())){
            return categoriaRepository.findByCategoria(categoria.getCategoria());
        }
        return categoriaRepository.save(categoria);
    }

    @DeleteMapping(path = {"/{categoriasId}"})
    public void  borrarCategoriaId(@PathVariable("categoriasId") Integer id){
        categoriaRepository.deleteById(id);
    }

    @PutMapping(path = {"/{categoriasId}"})
    public Categoria actualizarCategoria(@PathVariable("categoriasId") @RequestBody @Validated Integer id, Categoria categoria){
        categoria.setId(id);
        categoriaRepository.save(categoria);
        return categoria;
    }

}
