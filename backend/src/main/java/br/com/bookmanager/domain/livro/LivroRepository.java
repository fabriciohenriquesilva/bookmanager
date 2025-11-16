package br.com.bookmanager.domain.livro;

import br.com.bookmanager.domain.livro.dto.LivroReportDTO;
import br.com.bookmanager.domain.livro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @NativeQuery("SELECT * FROM vw_relatorio_autor_livro")
    List<LivroReportDTO> relatorio();
}
