package br.com.bookmanager.domain.autor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorUpdateRequestDTO(Integer codAu,
                                    @Size(max = 40) @NotBlank String nome) {
}
