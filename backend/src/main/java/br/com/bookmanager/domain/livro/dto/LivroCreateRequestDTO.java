package br.com.bookmanager.domain.livro.dto;

import br.com.bookmanager.domain.livro.model.Livro;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record LivroCreateRequestDTO(@NotBlank
                                    @Size(max = 40)
                                    String titulo,

                                    @Size(max = 40)
                                    String editora,

                                    Integer edicao,

                                    @Size(max = 4)
                                    String anoPublicacao,

                                    @Digits(integer = 10, fraction = 2)
                                    BigDecimal valor) {

    public Livro toEntity() {
        var livro = new Livro();
        livro.setTitulo(titulo);
        livro.setEditora(editora);
        livro.setEdicao(edicao);
        livro.setAnoPublicacao(anoPublicacao);
        livro.setValor(valor);

        return livro;
    }
}
