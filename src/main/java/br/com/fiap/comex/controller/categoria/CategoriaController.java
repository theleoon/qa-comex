package br.com.fiap.comex.controller.categoria;

import br.com.fiap.comex.controller.categoria.dto.CategoriaDto;
import br.com.fiap.comex.controller.categoria.form.CategoriaForm;
import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.service.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
@Profile(value = {"prod", "test"})
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDto> inserirNova(@Valid CategoriaForm form,
                                                    UriComponentsBuilder uriBuilder,
                                                    BindingResult result){
        if(result.hasFieldErrors()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Categoria novaCategoria = form.converter();
        service.cadastra(novaCategoria);

        URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(novaCategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(novaCategoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getById(@PathVariable Long id) {
        Optional<Categoria> optionalCategoria = service.getById(id);
        if (optionalCategoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CategoriaDto(optionalCategoria.get()));
    }
}
