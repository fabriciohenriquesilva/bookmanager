package br.com.bookmanager.domain.livroassunto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LivroAssuntoId implements Serializable {

    @Column(name = "livro_codl")
    private Integer livroCodL;

    @Column(name = "assunto_codas")
    private Integer assuntoCodAs;

    public LivroAssuntoId() {
    }

    public LivroAssuntoId(Integer codL, Integer codAs) {
        this.livroCodL = codL;
        this.assuntoCodAs = codAs;
    }

    public Integer getLivroCodL() {
        return livroCodL;
    }

    public void setLivroCodL(Integer livroId) {
        this.livroCodL = livroId;
    }

    public Integer getAssuntoCodAs() {
        return assuntoCodAs;
    }

    public void setAssuntoCodAs(Integer assuntoId) {
        this.assuntoCodAs = assuntoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivroAssuntoId that)) return false;
        return Objects.equals(livroCodL, that.livroCodL) && Objects.equals(assuntoCodAs, that.assuntoCodAs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroCodL, assuntoCodAs);
    }
}
