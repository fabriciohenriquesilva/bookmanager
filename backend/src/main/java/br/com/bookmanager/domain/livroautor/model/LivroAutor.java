package br.com.bookmanager.domain.livroautor.model;

import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.livro.model.Livro;
import jakarta.persistence.*;

@Entity
@Table(name = "livro_autor")
public class LivroAutor {

    @EmbeddedId
    private LivroAutorId id;

    @ManyToOne
    @MapsId("livroId")
    @JoinColumn(name = "livro_codl")
    private Livro livro;

    @ManyToOne
    @MapsId("autorId")
    @JoinColumn(name = "autor_codau")
    private Autor autor;

    public LivroAutorId getId() {
        return id;
    }

    public void setId(LivroAutorId id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
