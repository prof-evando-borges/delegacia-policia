package br.com.fiap.delegaciapolicia.chamadas.dto;

import br.com.fiap.delegaciapolicia.chamadas.entity.Chamada;
import br.com.fiap.delegaciapolicia.chamadas.entity.Disponibilidade;
import br.com.fiap.delegaciapolicia.chamadas.entity.UnidadeResponsavel;

import java.util.List;

public record UnidadeResponsavelResponse(
        Long id,
        String unidade,
        String descricao,
        String telefone,
        Disponibilidade disponibilidade,
        List<String> protocolosVinculados
) {

    public static UnidadeResponsavelResponse de(UnidadeResponsavel unidade) {
        return new UnidadeResponsavelResponse(
                unidade.getId(),
                unidade.getUnidade(),
                unidade.getDescricao(),
                unidade.getTelefone(),
                unidade.getDisponibilidade(),
                unidade.getChamadasVinculadas().stream().map(Chamada::getProtocolo).toList());
    }
}
