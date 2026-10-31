package br.com.fiap.delegaciapolicia.patio.controller;

import br.com.fiap.delegaciapolicia.patio.entity.VagaPatio;
import br.com.fiap.delegaciapolicia.patio.service.VagaPatioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patio/vagas")
@RequiredArgsConstructor
public class VagaPatioController {

    private final VagaPatioService vagaPatioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VagaPatio cadastrar(@Valid @RequestBody VagaPatio vaga) {
        return vagaPatioService.cadastrar(vaga);
    }

    @GetMapping
    public List<VagaPatio> listarTodos() {
        return vagaPatioService.listarTodos();
    }

    @GetMapping(params = "ocupada")
    public List<VagaPatio> listarPorOcupacao(@RequestParam Boolean ocupada) {
        return vagaPatioService.listarPorOcupacao(ocupada);
    }

    @GetMapping("/{id}")
    public VagaPatio buscarPorId(@PathVariable String id) {
        return vagaPatioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public VagaPatio atualizar(@PathVariable String id, @Valid @RequestBody VagaPatio vaga) {
        return vagaPatioService.atualizar(id, vaga);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String id) {
        vagaPatioService.remover(id);
    }
}
