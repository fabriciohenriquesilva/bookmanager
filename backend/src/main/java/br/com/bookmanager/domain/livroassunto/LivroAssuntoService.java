package br.com.bookmanager.domain.livroassunto;

import br.com.bookmanager.domain.assunto.AssuntoRepository;
import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.livro.LivroRepository;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroassunto.model.LivroAssunto;
import br.com.bookmanager.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroAssuntoService {

    @Autowired
    private LivroAssuntoRepository livroAssuntoRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Autowired
    private LivroRepository livroRepository;

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

    public Assunto getByAssuntoCodAs(Integer codAs) throws RegistroNaoEncontradoException {
        return assuntoRepository.findById(codAs)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RegistroNaoEncontradoException(codAs));
    }

    public void deleteByLivroAndAssunto(Integer codL, Integer codAs) {
        livroAssuntoRepository.deleteByLivroCodLAndAssuntoCodAs(codL, codAs);
    }

    public Livro getByLivroCodL(Integer codL) {
        return livroRepository.findById(codL)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RegistroNaoEncontradoException(codL));
    }
}
