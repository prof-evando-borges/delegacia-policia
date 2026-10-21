package br.com.fiap.delegaciapolicia.chamadas.service;

import br.com.fiap.delegaciapolicia.chamadas.dto.ChamadaRequest;
import br.com.fiap.delegaciapolicia.chamadas.dto.ChamadaResponse;
import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.StatusChamada;
import br.com.fiap.delegaciapolicia.chamadas.repository.ChamadaRepository;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RecursoNaoEncontradoException;
import br.com.fiap.delegaciapolicia.compartilhado.excecao.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChamadaService {

    private static final DateTimeFormatter FORMATO_PROTOCOLO = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final ChamadaRepository chamadaRepository;
    private final AtendenteService atendenteService;

    @Transactional
    public ChamadaResponse salvarChamada(ChamadaRequest request) {
        LocalDateTime agora = LocalDateTime.now();

        Chamada chamada = new Chamada();
        chamada.setProtocolo(gerarProtocolo(agora));
        chamada.setNatureza(request.natureza());
        chamada.setIdSolicitante(request.idSolicitante());
        chamada.setAtendente(atendenteService.buscarEntidadePorMatricula(request.matriculaAtendente()));
        chamada.setDescricao(request.descricao());
        chamada.setEndereco(request.endereco());
        chamada.setStatus(StatusChamada.ABERTA);
        chamada.setDataHora(agora);
        return ChamadaResponse.de(chamadaRepository.save(chamada));
    }

    @Transactional(readOnly = true)
    public ChamadaResponse buscarChamadaPorProtocolo(String protocolo) {
        return ChamadaResponse.de(buscarEntidadePorProtocolo(protocolo));
    }

    @Transactional(readOnly = true)
    public List<ChamadaResponse> listarChamadas() {
        return chamadaRepository.findAll().stream().map(ChamadaResponse::de).toList();
    }

    @Transactional
    public ChamadaResponse encerrarChamada(String protocolo, String desfecho) {
        Chamada chamada = buscarEntidadePorProtocolo(protocolo);
        if (chamada.getStatus() == StatusChamada.ENCERRADA) {
            throw new RegraDeNegocioException("A chamada " + protocolo + " já está encerrada.");
        }
        chamada.setStatus(StatusChamada.ENCERRADA);
        chamada.setDesfecho(desfecho);
        return ChamadaResponse.de(chamada);
    }

    /** Uso interno do módulo (ex.: {@link UnidadeResponsavelService}). */
    @Transactional(readOnly = true)
    public Chamada buscarEntidadePorProtocolo(String protocolo) {
        return chamadaRepository.findByProtocolo(protocolo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Chamada com protocolo " + protocolo + " não encontrada."));
    }

    private String gerarProtocolo(LocalDateTime dataHora) {
        String sufixo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "CHM-" + dataHora.format(FORMATO_PROTOCOLO) + "-" + sufixo;
    }
}
