package br.com.bookmanager.domain.livro.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record LivroUpdateRequestDTO(@NotNull
                                    Integer codL,

                                    @NotBlank
                                    @Size(max = 40)
                                    String titulo,

                                    @Size(max = 40)
                                    String editora,

                                    Integer edicao,

                                    @Size(max = 4)
                                    String anoPublicacao,

                                    @Digits(integer = 10, fraction = 2)
                                    BigDecimal valor) {
}