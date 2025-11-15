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
    @MapsId("livroCodL")
    @JoinColumn(name = "livro_codl")
    private Livro livro;

    @ManyToOne
    @MapsId("assuntoCodAs")
    @JoinColumn(name = "assunto_codas")
    private Assunto assunto;

    public LivroAssunto() {
    }

    public LivroAssunto(Livro livro, Assunto assunto) {
        this.id = new LivroAssuntoId(livro.getCodL(), assunto.getCodAs());
        this.livro = livro;
        this.assunto = assunto;
    }

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
