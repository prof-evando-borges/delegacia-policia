package br.com.fiap.delegaciapolicia.chamadas.controller;

import br.com.fiap.delegaciapolicia.chamadas.dto.ChamadaRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.ChamadaResponse;
import br.com.fiap.delegaciapolicia.chamadas.dto.EncerramentoRequest;
import br.com.fiap.delegaciapolicia.chamadas.service.ChamadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chamadas")
@RequiredArgsConstructor
public class ChamadaController {

    private final ChamadaService chamadaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChamadaResponse salvar(@RequestBody @Valid ChamadaRequest request) {
        return chamadaService.salvarChamada(request);
    }

    @GetMapping
    public List<ChamadaResponse> listar() {
        return chamadaService.listarChamadas();
    }

    @GetMapping("/{protocolo}")
    public ChamadaResponse buscarPorProtocolo(@PathVariable String protocolo) {
        return chamadaService.buscarChamadaPorProtocolo(protocolo);
    }

    @PatchMapping("/{protocolo}/encerramento")
    public ChamadaResponse encerrar(@PathVariable String protocolo, @RequestBody @Valid EncerramentoRequest request) {
        return chamadaService.encerrarChamada(protocolo, request.desfecho());
    }
}
