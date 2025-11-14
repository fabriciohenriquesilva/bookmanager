package br.com.bookmanager.domain.livro;

import br.com.bookmanager.domain.livro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
