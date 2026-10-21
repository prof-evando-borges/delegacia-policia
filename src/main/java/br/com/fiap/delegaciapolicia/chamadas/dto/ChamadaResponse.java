package br.com.fiap.delegaciapolicia.chamadas.dto;

import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.StatusChamada;

import java.time.LocalDateTime;

public record ChamadaResponse(
        Long id,
        String protocolo,
        String natureza,
        Long idSolicitante,
        String matriculaAtendente,
        String descricao,
        String endereco,
        StatusChamada status,
        LocalDateTime dataHora,
        String desfecho,
        Long idUnidadeResponsavel
) {

    public static ChamadaResponse de(Chamada chamada) {
        return new ChamadaResponse(
                chamada.getId(),
                chamada.getProtocolo(),
                chamada.getNatureza(),
                chamada.getIdSolicitante(),
                chamada.getAtendente().getMatricula(),
                chamada.getDescricao(),
                chamada.getEndereco(),
                chamada.getStatus(),
                chamada.getDataHora(),
                chamada.getDesfecho(),
                chamada.getUnidadeResponsavel() == null ? null : chamada.getUnidadeResponsavel().getId());
    }
}
