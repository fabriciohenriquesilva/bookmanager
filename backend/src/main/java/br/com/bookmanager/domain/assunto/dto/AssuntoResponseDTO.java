package br.com.bookmanager.domain.assunto.dto;

import br.com.bookmanager.domain.assunto.model.Assunto;

public record AssuntoResponseDTO(Integer codAs,
                                 String nome) {

    public AssuntoResponseDTO(Assunto assunto) {
        this(assunto.getCodAs(), assunto.getDescricao());
    }
}
