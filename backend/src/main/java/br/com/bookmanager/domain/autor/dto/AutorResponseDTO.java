package br.com.bookmanager.domain.autor.dto;

import br.com.bookmanager.domain.autor.model.Autor;

public record AutorResponseDTO(Integer codAu,
                               String nome) {

    public AutorResponseDTO(Autor autor){
        this(autor.getCodAu(), autor.getNome());
    }
}
