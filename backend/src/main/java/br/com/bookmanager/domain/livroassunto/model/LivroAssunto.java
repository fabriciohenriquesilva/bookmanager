package br.com.bookmanager.domain.livroassunto.model;

import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.livro.model.Livro;
import jakarta.persistence.*;

@Entity
@Table(name = "livro_assunto")
public class LivroAssunto {

    @EmbeddedId
    private LivroAssuntoId id;

    @ManyToOne
    @MapsId("livroId")
    @JoinColumn(name = "livro_codl")
    private Livro livro;

    @ManyToOne
    @MapsId("assuntoId")
    @JoinColumn(name = "assunto_codas")
    private Assunto assunto;

    public LivroAssuntoId getId() {
        return id;
    }

    public void setId(LivroAssuntoId id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }
}
