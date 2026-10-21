package br.com.fiap.delegaciapolicia.chamadas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadaRequest(
        @NotBlank @Size(max = 60) String natureza,
        @NotNull Long idSolicitante,
        @NotBlank @Size(max = 20) String matriculaAtendente,
        @NotBlank @Size(max = 500) String descricao,
        @NotBlank @Size(max = 200) String endereco
) {
}
