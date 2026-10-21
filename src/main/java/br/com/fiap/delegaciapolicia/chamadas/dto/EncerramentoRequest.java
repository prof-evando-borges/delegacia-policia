package br.com.fiap.delegaciapolicia.chamadas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EncerramentoRequest(@NotBlank @Size(max = 500) String desfecho) {
}
