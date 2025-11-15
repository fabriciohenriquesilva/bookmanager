package br.com.bookmanager.domain.livro;

import br.com.bookmanager.domain.assunto.dto.AssuntoResponseDTO;
import br.com.bookmanager.domain.autor.dto.AutorResponseDTO;
import br.com.bookmanager.domain.livro.dto.LivroCreateRequestDTO;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import br.com.bookmanager.domain.livro.dto.LivroUpdateRequestDTO;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroassunto.LivroAssuntoService;
import br.com.bookmanager.domain.livroassunto.model.LivroAssunto;
import br.com.bookmanager.domain.livroautor.LivroAutorService;
import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import br.com.bookmanager.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroAssuntoService livroAssuntoService;

    @Autowired
    private LivroAutorService livroAutorService;

    public LivroResponseDTO create(LivroCreateRequestDTO request) {
        Livro entity = request.toEntity();

        Livro livro = livroRepository.save(entity);

        return new LivroResponseDTO(livro);
    }

    public Page<LivroResponseDTO> findAll(Pageable pageable) {
        return livroRepository.findAll(pageable)
                .map(LivroResponseDTO::new);
    }

    public LivroResponseDTO findById(Integer codL) throws RegistroNaoEncontradoException {
        Livro livro = getLivroOrThrowException(codL);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO update(LivroUpdateRequestDTO request) {
        Livro livro = getLivroOrThrowException(request.codL());

        livro.setTitulo(request.titulo());
        livro.setEditora(request.editora());
        livro.setEdicao(request.edicao());
        livro.setAnoPublicacao(request.anoPublicacao());
        livro.setValor(request.valor());

        return new LivroResponseDTO(livroRepository.save(livro));
    }

    public void delete(Integer codL) throws RegistroNaoEncontradoException {
        Livro livro = getLivroOrThrowException(codL);

        livroAssuntoService.findByLivro(livro)
                .forEach(livroAssunto -> livroAssuntoService.delete(livroAssunto));

        livroAutorService.findByLivro(livro)
                .forEach(livroAutor -> livroAutorService.delete(livroAutor));

        livroRepository.delete(livro);
    }

    public Livro getLivroOrThrowException(Integer codL) {
        return livroRepository.findById(codL)
                .orElseThrow(() -> new RegistroNaoEncontradoException(codL));
    }

    public List<AssuntoResponseDTO> getAssuntos(Integer codL) {
        Livro livro = getLivroOrThrowException(codL);

        return livroAssuntoService.findByLivro(livro)
                .stream()
                .map(LivroAssunto::getAssunto)
                .map(AssuntoResponseDTO::new)
                .toList();
    }

    public List<AutorResponseDTO> getAutores(Integer codL) {
        Livro livro = getLivroOrThrowException(codL);

        return livroAutorService.findByLivro(livro)
                .stream()
                .map(LivroAutor::getAutor)
                .map(AutorResponseDTO::new)
                .toList();
    }
}
