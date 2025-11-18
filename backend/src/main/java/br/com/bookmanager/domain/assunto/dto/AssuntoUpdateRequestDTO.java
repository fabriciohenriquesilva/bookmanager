package br.com.bookmanager.domain.assunto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AssuntoUpdateRequestDTO(@NotNull
                                      Integer codAs,

                                      @Size(max = 20)
                                      @NotBlank
                                      String descricao,

                                      List<Integer> livrosId) {
}
