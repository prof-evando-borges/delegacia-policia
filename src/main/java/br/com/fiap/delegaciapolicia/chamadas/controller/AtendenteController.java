package br.com.fiap.delegaciapolicia.chamadas.controller;

import br.com.fiap.delegaciapolicia.chamadas.dto.AtendenteRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.AtendenteResponse;
import br.com.fiap.delegaciapolicia.chamadas.service.AtendenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chamadas/atendentes")
@RequiredArgsConstructor
public class AtendenteController {

    private final AtendenteService atendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtendenteResponse salvar(@RequestBody @Valid AtendenteRequest request) {
        return atendenteService.salvarAtendente(request);
    }

    @GetMapping
    public List<AtendenteResponse> listar() {
        return atendenteService.listarAtendentes();
    }

    @GetMapping("/{matricula}")
    public AtendenteResponse buscarPorMatricula(@PathVariable String matricula) {
        return atendenteService.buscarAtendentePorMatricula(matricula);
    }
}
