package br.com.fiap.delegaciapolicia.estoque.controller;

import br.com.fiap.delegaciapolicia.estoque.entity.CategoriaEstoque;
import br.com.fiap.delegaciapolicia.estoque.service.CategoriaEstoqueService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estoque/categorias")
@RequiredArgsConstructor
public class CategoriaEstoqueController {

    private final CategoriaEstoqueService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaEstoque> cadastrar(@Valid @RequestBody CategoriaEstoque categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.cadastrar(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaEstoque>> listarTodos() {
        return ResponseEntity.ok(categoriaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEstoque> buscarPorId(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEstoque> atualizar(@PathVariable("id") String id,
            @Valid @RequestBody CategoriaEstoque categoria) {
        return ResponseEntity.ok(categoriaService.atualizar(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") String id) {
        categoriaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
