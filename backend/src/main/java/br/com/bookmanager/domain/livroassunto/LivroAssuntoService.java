package br.com.bookmanager.domain.livroassunto;

import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroassunto.model.LivroAssunto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroAssuntoService {

    @Autowired
    private LivroAssuntoRepository livroAssuntoRepository;

    public LivroAssunto save(LivroAssunto livroAssunto) {
        return livroAssuntoRepository.save(livroAssunto);
    }

    public void delete(LivroAssunto livroAssunto) {
        livroAssuntoRepository.delete(livroAssunto);
    }

    public List<LivroAssunto> findByAssunto(Assunto assunto) {
        return livroAssuntoRepository.findByAssunto(assunto);
    }

    public List<LivroAssunto> findByLivro(Livro livro) {
        return livroAssuntoRepository.findByLivro(livro);
    }

}
