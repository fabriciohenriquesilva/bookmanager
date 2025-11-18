package br.com.bookmanager.domain.livroautor;

import br.com.bookmanager.domain.autor.AutorRepository;
import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.livro.LivroRepository;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import br.com.bookmanager.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LivroAutorService {

    @Autowired
    private LivroAutorRepository livroAutorRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public LivroAutor save(LivroAutor livroAutor) {
        return livroAutorRepository.save(livroAutor);
    }

    public void delete(LivroAutor livroAutor) {
        livroAutorRepository.delete(livroAutor);
    }

    public void deleteByLivroAndAutor(Integer codL, Integer codAu) {
        livroAutorRepository.deleteByLivroCodLAndAutorCodAu(codL, codAu);
    }

    public Autor getByAutorCodAu(Integer codAu) throws RegistroNaoEncontradoException {
        return autorRepository.findById(codAu)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RegistroNaoEncontradoException(codAu));
    }

    public List<LivroAutor> findByAutor(Autor autor) {
        return livroAutorRepository.findByAutor(autor);
    }

    public List<LivroAutor> findByLivro(Livro livro) {
        return livroAutorRepository.findByLivro(livro);
    }

    public Livro getByLivroCodL(Integer codL) {
        return livroRepository.findById(codL)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RegistroNaoEncontradoException(codL));
    }
}
