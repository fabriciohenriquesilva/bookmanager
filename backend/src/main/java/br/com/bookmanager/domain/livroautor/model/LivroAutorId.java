package br.com.bookmanager.domain.livroautor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LivroAutorId implements Serializable {

    @Column(name = "livro_codl")
    private Integer livroCodL;

    @Column(name = "autor_codau")
    private Integer autorCodAu;

    public LivroAutorId() {
    }

    public LivroAutorId(Integer livroCodL, Integer autorCodAu) {
        this.livroCodL = livroCodL;
        this.autorCodAu = autorCodAu;
    }

    public Integer getLivroCodL() {
        return livroCodL;
    }

    public void setLivroCodL(Integer livroId) {
        this.livroCodL = livroId;
    }

    public Integer getAutorCodAu() {
        return autorCodAu;
    }

    public void setAutorCodAu(Integer autorId) {
        this.autorCodAu = autorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivroAutorId that)) return false;
        return Objects.equals(livroCodL, that.livroCodL) &&
                Objects.equals(autorCodAu, that.autorCodAu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroCodL, autorCodAu);
    }
}
