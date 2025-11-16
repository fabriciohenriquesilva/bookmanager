package br.com.bookmanager.domain.livro.dto;

public class LivroReportDTO {

    private Integer codAutor;
    private Integer codLivro;
    private String autor;
    private String livro;
    private String assuntos;
    private String editora;
    private Integer edicao;
    private String anoPublicacao;

    public LivroReportDTO() {
    }

    public LivroReportDTO(Integer codLivro, Integer codAutor, String autor, String livro, String assuntos, String editora, Integer edicao, String anoPublicacao) {
        this.codLivro = codLivro;
        this.codAutor = codAutor;
        this.autor = autor;
        this.livro = livro;
        this.assuntos = assuntos;
        this.editora = editora;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }

    public Integer getCodAutor() {
        return codAutor;
    }

    public void setCodAutor(Integer codAutor) {
        this.codAutor = codAutor;
    }

    public Integer getCodLivro() {
        return codLivro;
    }

    public void setCodLivro(Integer codLivro) {
        this.codLivro = codLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(String assuntos) {
        this.assuntos = assuntos;
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
}
