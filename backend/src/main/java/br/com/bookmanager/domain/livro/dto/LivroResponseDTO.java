package br.com.bookmanager.domain.livro.dto;

import br.com.bookmanager.domain.livro.model.Livro;

import java.math.BigDecimal;

public record LivroResponseDTO(Integer codL,
                               String titulo,
                               String editora,
                               Integer edicao,
                               String anoPublicacao,
                               BigDecimal valor) {

    public LivroResponseDTO(Livro livro) {
        this(livro.getCodL(), livro.getTitulo(), livro.getEditora(), livro.getEdicao(), livro.getAnoPublicacao(), livro.getValor());
    }
}
