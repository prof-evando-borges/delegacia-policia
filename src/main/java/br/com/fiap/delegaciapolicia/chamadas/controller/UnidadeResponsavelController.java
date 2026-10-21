package br.com.fiap.delegaciapolicia.chamadas.controller;

import br.com.fiap.delegaciapolicia.chamadas.dto.DisponibilidadeRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.UnidadeResponsavelRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.UnidadeResponsavelResponse;
import br.com.fiap.delegaciapolicia.chamadas.service.UnidadeResponsavelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/chamadas/unidades")
@RequiredArgsConstructor
public class UnidadeResponsavelController {

    private final UnidadeResponsavelService unidadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadeResponsavelResponse salvar(@RequestBody @Valid UnidadeResponsavelRequest request) {
        return unidadeService.salvarUnidadeResponsavel(request);
    }

    @GetMapping
    public List<UnidadeResponsavelResponse> listar() {
        return unidadeService.listarUnidadesResponsaveis();
    }

    @GetMapping("/{id}")
    public UnidadeResponsavelResponse buscarPorId(@PathVariable Long id) {
        return unidadeService.buscarUnidadePorId(id);
    }

    @PatchMapping("/{id}/disponibilidade")
    public UnidadeResponsavelResponse atualizarDisponibilidade(@PathVariable Long id,
                                                               @RequestBody @Valid DisponibilidadeRequest request) {
        return unidadeService.atualizarDisponibilidadeUnidade(id, request.disponibilidade());
    }

    @PostMapping("/{id}/chamadas/{protocolo}")
    public UnidadeResponsavelResponse vincularChamada(@PathVariable Long id, @PathVariable String protocolo) {
        return unidadeService.vincularChamada(id, protocolo);
    }

    @DeleteMapping("/{id}/chamadas/{protocolo}")
    public UnidadeResponsavelResponse desvincularChamada(@PathVariable Long id, @PathVariable String protocolo) {
        return unidadeService.desvincularChamada(id, protocolo);
    }
}
