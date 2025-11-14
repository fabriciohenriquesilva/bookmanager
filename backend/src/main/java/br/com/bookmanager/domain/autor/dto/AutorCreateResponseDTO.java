package br.com.bookmanager.domain.autor.dto;

import br.com.bookmanager.domain.autor.model.Autor;

public record AutorCreateResponseDTO(Integer codAu,
                                     String nome) {

    public AutorCreateResponseDTO(Autor autor){
        this(autor.getCodAu(), autor.getNome());
    }
}
