package br.com.bookmanager.domain.autor;

import br.com.bookmanager.domain.autor.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

    List<Autor> findByNomeIgnoreCaseContaining(String nome);

}
