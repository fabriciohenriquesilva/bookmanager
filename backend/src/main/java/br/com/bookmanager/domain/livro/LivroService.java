package br.com.bookmanager.domain.livro;

import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.infra.exception.RecursoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro getLivroOrThrowException(Integer codL) {
        return livroRepository.findById(codL)
                .orElseThrow(() -> new RecursoInexistenteException(codL));
    }
}
