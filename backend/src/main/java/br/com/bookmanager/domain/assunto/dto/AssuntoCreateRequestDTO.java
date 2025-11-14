package br.com.bookmanager.domain.assunto.dto;

import br.com.bookmanager.domain.assunto.model.Assunto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AssuntoCreateRequestDTO(@Size(max = 20) @NotBlank String descricao,
                                      List<Integer> livrosId) {

    public Assunto toEntity() {
        var assunto = new Assunto();
        assunto.setDescricao(descricao);

        return assunto;
    }
}
