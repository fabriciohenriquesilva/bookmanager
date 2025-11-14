package br.com.bookmanager.domain.livroautor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LivroAutorId implements Serializable {

    @Column(name = "livro_codl")
    private Integer livroId;

    @Column(name = "autor_codau")
    private Integer autorId;

    public Integer getLivroId() {
        return livroId;
    }

    public void setLivroId(Integer livroId) {
        this.livroId = livroId;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivroAutorId that)) return false;
        return Objects.equals(livroId, that.livroId) &&
                Objects.equals(autorId, that.autorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroId, autorId);
    }
}
