package br.com.fiap.delegaciapolicia.chamadas.dto;

import br.com.fiap.delegaciapolicia.chamadas.entity.Disponibilidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UnidadeResponsavelRequest(
        @NotBlank @Size(max = 100) String unidade,
        @Size(max = 255) String descricao,
        @NotBlank @Size(max = 20) String telefone,
        @NotNull Disponibilidade disponibilidade
) {
}
