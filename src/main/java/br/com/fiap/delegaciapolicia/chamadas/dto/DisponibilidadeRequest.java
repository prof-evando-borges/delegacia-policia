package br.com.fiap.delegaciapolicia.chamadas.dto;

import br.com.fiap.delegaciapolicia.chamadas.entity.Disponibilidade;
import jakarta.validation.constraints.NotNull;

public record DisponibilidadeRequest(@NotNull Disponibilidade disponibilidade) {
}
