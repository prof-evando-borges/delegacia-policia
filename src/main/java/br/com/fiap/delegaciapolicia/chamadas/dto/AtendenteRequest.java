package br.com.fiap.delegaciapolicia.chamadas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtendenteRequest(
        @NotNull Long idPolicial,
        @NotBlank @Size(max = 20) String matricula,
        @NotBlank @Size(max = 20) String turno
) {
}
