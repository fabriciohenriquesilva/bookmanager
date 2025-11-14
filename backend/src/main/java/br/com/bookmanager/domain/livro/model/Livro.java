package br.com.bookmanager.domain.livro.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codl")
    private Integer codL;

    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;

    @Column(name = "editora", length = 40)
    private String editora;

    @Column(name = "edicao")
    private Integer edicao;

    @Column(name = "anopublicacao", length = 4)
    private String anoPublicacao;

    @OneToMany(mappedBy = "livro")
    private List<LivroAutor> autores;

    @OneToMany(mappedBy = "livro")
    private List<LivroAssunto> assuntos;

    public Integer getCodL() {
        return codL;
    }

    public void setCodL(Integer id) {
        this.codL = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public List<LivroAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<LivroAutor> autores) {
        this.autores = autores;
    }

    public List<LivroAssunto> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<LivroAssunto> assuntos) {
        this.assuntos = assuntos;
    }
}
