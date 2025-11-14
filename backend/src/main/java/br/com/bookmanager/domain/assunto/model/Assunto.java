package br.com.bookmanager.domain.assunto.model;

import br.com.bookmanager.domain.livro.model.LivroAssunto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "assunto")
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codas")
    private Integer codAs;

    @Column(name = "descricao", length = 20, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "assunto")
    private List<LivroAssunto> livros;

    public Integer getCodAs() {
        return codAs;
    }

    public void setCodAs(Integer id) {
        this.codAs = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<LivroAssunto> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroAssunto> livros) {
        this.livros = livros;
    }
}