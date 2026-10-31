package br.com.fiap.delegaciapolicia.estoque.controller;

import br.com.fiap.delegaciapolicia.estoque.entity.ItemEstoque;
import br.com.fiap.delegaciapolicia.estoque.service.ItemEstoqueService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estoque/itens")
@RequiredArgsConstructor
public class ItemEstoqueController {

    private final ItemEstoqueService itemService;

    @PostMapping
    public ResponseEntity<ItemEstoque> cadastrar(@Valid @RequestBody ItemEstoque item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.cadastrar(item));
    }

    @GetMapping
    public ResponseEntity<List<ItemEstoque>> listar(@RequestParam(name = "nome", required = false) String nome) {
        if (nome != null) {
            return ResponseEntity.ok(itemService.buscarPorNome(nome));
        }
        return ResponseEntity.ok(itemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoque> buscarPorId(@PathVariable("id") String id) {
        return ResponseEntity.ok(itemService.buscarPorId(id));
    }

    @GetMapping("/busca/categoria/{idCategoria}")
    public ResponseEntity<List<ItemEstoque>> buscarPorCategoria(@PathVariable("idCategoria") String idCategoria) {
        return ResponseEntity.ok(itemService.buscarPorCategoria(idCategoria));
    }

    @GetMapping("/busca/ativos")
    public ResponseEntity<List<ItemEstoque>> buscarAtivos() {
        return ResponseEntity.ok(itemService.buscarAtivos());
    }

    @GetMapping("/busca/estoque-baixo")
    public ResponseEntity<List<ItemEstoque>> buscarComEstoqueBaixo(@RequestParam("limite") int limite) {
        return ResponseEntity.ok(itemService.buscarComEstoqueBaixo(limite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemEstoque> atualizar(@PathVariable("id") String id,
            @Valid @RequestBody ItemEstoque item) {
        return ResponseEntity.ok(itemService.atualizar(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") String id) {
        itemService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
