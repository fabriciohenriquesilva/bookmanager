package br.com.bookmanager.domain.assunto;

import br.com.bookmanager.domain.assunto.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {

    List<Assunto> findByDescricaoIgnoreCaseContaining(String descricao);
}
