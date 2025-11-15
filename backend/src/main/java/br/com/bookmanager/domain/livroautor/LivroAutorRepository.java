package br.com.bookmanager.domain.livroautor;

import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroAutorRepository extends JpaRepository<LivroAutor, Integer> {

    List<LivroAutor> findByAutor(Autor autor);

    List<LivroAutor> findByLivro(Livro livro);

    void deleteByLivroCodLAndAutorCodAu(Integer codL, Integer codAu);
}
