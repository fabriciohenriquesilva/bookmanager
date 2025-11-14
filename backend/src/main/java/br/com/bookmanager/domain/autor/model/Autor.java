package br.com.bookmanager.domain.autor.model;

import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codau")
    private Integer codAu;

    @Column(length = 40, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "autor")
    private List<LivroAutor> livros;

    public Integer getCodAu() {
        return codAu;
    }

    public void setCodAu(Integer codAu) {
        this.codAu = codAu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LivroAutor> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroAutor> livros) {
        this.livros = livros;
    }
}
