package br.com.bookmanager.domain.autor.dto;

import br.com.bookmanager.domain.autor.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AutorCreateRequestDTO(@Size(max = 40) @NotBlank String nome,
                                    List<Integer> livrosId) {

    public Autor toEntity() {
        var autor = new Autor();
        autor.setNome(nome);

        return autor;
    }
}
