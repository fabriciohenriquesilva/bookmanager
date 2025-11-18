package br.com.bookmanager.domain.autor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AutorUpdateRequestDTO(@NotNull
                                    Integer codAu,

                                    @Size(max = 40)
                                    @NotBlank
                                    String nome,

                                    List<Integer> livrosId) {
}
