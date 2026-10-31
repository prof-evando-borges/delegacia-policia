package br.com.fiap.delegaciapolicia.estoque.controller;

import br.com.fiap.delegaciapolicia.estoque.entity.MovimentacaoEstoque;
import br.com.fiap.delegaciapolicia.estoque.entity.TipoMovimentacao;
import br.com.fiap.delegaciapolicia.estoque.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/v1/estoque/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoEstoque> cadastrar(@Valid @RequestBody MovimentacaoEstoque movimentacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoService.cadastrar(movimentacao));
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoque>> listar(
            @RequestParam(name = "tipo", required = false) TipoMovimentacao tipo) {
        if (tipo != null) {
            return ResponseEntity.ok(movimentacaoService.buscarPorTipo(tipo));
        }
        return ResponseEntity.ok(movimentacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoque> buscarPorId(@PathVariable("id") String id) {
        return ResponseEntity.ok(movimentacaoService.buscarPorId(id));
    }

    @GetMapping("/busca/item/{idItem}")
    public ResponseEntity<List<MovimentacaoEstoque>> buscarPorItem(@PathVariable("idItem") String idItem) {
        return ResponseEntity.ok(movimentacaoService.buscarPorItem(idItem));
    }

    @GetMapping("/busca/policial/{idPolicial}")
    public ResponseEntity<List<MovimentacaoEstoque>> buscarPorPolicial(@PathVariable("idPolicial") String idPolicial) {
        return ResponseEntity.ok(movimentacaoService.buscarPorPolicial(idPolicial));
    }

    @GetMapping("/busca/periodo")
    public ResponseEntity<List<MovimentacaoEstoque>> buscarPorPeriodo(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(movimentacaoService.buscarPorPeriodo(inicio, fim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoque> atualizar(@PathVariable("id") String id,
            @Valid @RequestBody MovimentacaoEstoque movimentacao) {
        return ResponseEntity.ok(movimentacaoService.atualizar(id, movimentacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") String id) {
        movimentacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
