package br.com.bookmanager.domain.autor;

import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.autor.dto.AutorCreateRequestDTO;
import br.com.bookmanager.domain.autor.dto.AutorCreateResponseDTO;
import br.com.bookmanager.domain.autor.dto.AutorResponseDTO;
import br.com.bookmanager.domain.autor.dto.AutorUpdateRequestDTO;
import br.com.bookmanager.domain.livro.LivroService;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import br.com.bookmanager.domain.livroautor.LivroAutorService;
import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import br.com.bookmanager.infra.exception.RecursoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroAutorService livroAutorService;

    public AutorCreateResponseDTO create(AutorCreateRequestDTO request) {
        Autor entity = request.toEntity();

        Autor autor = autorRepository.save(entity);

        if (request.livrosId() != null && !request.livrosId().isEmpty()) {
            for (Integer livroId : request.livrosId()) {
                var livro = livroService.getLivroOrThrowException(livroId);
                LivroAutor livroAutor = new LivroAutor();
                livroAutor.setAutor(autor);
                livroAutor.setLivro(livro);

                livroAutorService.save(livroAutor);
            }
        }

        return new AutorCreateResponseDTO(autor);
    }

    public Page<AutorResponseDTO> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable)
                .map(AutorResponseDTO::new);
    }

    public AutorResponseDTO findById(Integer codAu) throws RecursoInexistenteException {
        Autor autor = getAutorOrThrowException(codAu);

        return new AutorResponseDTO(autor);
    }

    public AutorResponseDTO update(AutorUpdateRequestDTO request) {
        Autor autor = getAutorOrThrowException(request.codAu());

        autor.setNome(request.nome());

        return new AutorResponseDTO(autorRepository.save(autor));
    }

    public void delete(Integer codAu) throws RecursoInexistenteException {
        Autor autor = getAutorOrThrowException(codAu);

        List<LivroAutor> livros = livroAutorService.findByAutor(autor);
        livros.forEach(livroAutor -> livroAutorService.delete(livroAutor));

        autorRepository.delete(autor);
    }

    public List<LivroResponseDTO> getLivros(Integer codAu) {
        Autor autor = getAutorOrThrowException(codAu);

        List<LivroAutor> livrosByAutor = livroAutorService.findByAutor(autor);

        return livrosByAutor
                .stream()
                .map(LivroAutor::getLivro)
                .map(LivroResponseDTO::new)
                .toList();
    }

    public Autor getAutorOrThrowException(Integer codAu) {
        return autorRepository.findById(codAu)
                .orElseThrow(() -> new RecursoInexistenteException(codAu));
    }

}
