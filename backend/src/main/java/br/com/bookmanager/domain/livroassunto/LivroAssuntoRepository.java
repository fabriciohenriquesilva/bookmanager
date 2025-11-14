package br.com.bookmanager.domain.livroassunto;

import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroassunto.model.LivroAssunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroAssuntoRepository extends JpaRepository<LivroAssunto, Integer> {

    List<LivroAssunto> findByLivro(Livro livro);

    List<LivroAssunto> findByAssunto(Assunto assunto);

}
