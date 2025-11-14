package br.com.bookmanager.domain.assunto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssuntoUpdateRequestDTO(Integer codAs,
                                      @Size(max = 20) @NotBlank String descricao) {
}
