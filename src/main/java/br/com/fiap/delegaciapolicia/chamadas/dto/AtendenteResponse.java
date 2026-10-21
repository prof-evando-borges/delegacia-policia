package br.com.fiap.delegaciapolicia.chamadas.dto;

import br.com.fiap.delegaciapolicia.chamadas.entity.Atendente;

public record AtendenteResponse(Long id, Long idPolicial, String matricula, String turno) {

    public static AtendenteResponse de(Atendente atendente) {
        return new AtendenteResponse(
                atendente.getId(),
                atendente.getIdPolicial(),
                atendente.getMatricula(),
                atendente.getTurno());
    }
}
