package br.com.bookmanager.domain.livroautor;

import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LivroAutorService {

    @Autowired
    private LivroAutorRepository livroAutorRepository;

    public LivroAutor save(LivroAutor livroAutor) {
        return livroAutorRepository.save(livroAutor);
    }

    public void delete(LivroAutor livroAutor) {
        livroAutorRepository.delete(livroAutor);
    }

    public List<LivroAutor> findByAutor(Autor autor) {
        return livroAutorRepository.findByAutor(autor);
    }

    public List<LivroAutor> findByLivro(Livro livro) {
        return livroAutorRepository.findByLivro(livro);
    }

}
