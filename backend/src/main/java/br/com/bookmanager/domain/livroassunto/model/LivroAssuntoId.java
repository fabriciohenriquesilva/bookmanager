package br.com.bookmanager.domain.livroassunto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LivroAssuntoId implements Serializable {

    @Column(name = "livro_codl")
    private Integer livroId;

    @Column(name = "assunto_codas")
    private Integer assuntoId;

    public Integer getLivroId() {
        return livroId;
    }

    public void setLivroId(Integer livroId) {
        this.livroId = livroId;
    }

    public Integer getAssuntoId() {
        return assuntoId;
    }

    public void setAssuntoId(Integer assuntoId) {
        this.assuntoId = assuntoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivroAssuntoId that)) return false;
        return Objects.equals(livroId, that.livroId) && Objects.equals(assuntoId, that.assuntoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroId, assuntoId);
    }
}
