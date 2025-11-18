package br.com.bookmanager.domain.autor;

import br.com.bookmanager.domain.autor.dto.AutorCreateRequestDTO;
import br.com.bookmanager.domain.autor.dto.AutorResponseDTO;
import br.com.bookmanager.domain.autor.dto.AutorUpdateRequestDTO;
import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroautor.LivroAutorService;
import br.com.bookmanager.domain.livroautor.model.LivroAutor;
import br.com.bookmanager.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroAutorService livroAutorService;

    public AutorResponseDTO create(AutorCreateRequestDTO request) {
        Autor entity = request.toEntity();

        Autor autor = autorRepository.save(entity);

        return new AutorResponseDTO(autor);
    }

    public Page<AutorResponseDTO> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable)
                .map(AutorResponseDTO::new);
    }

    public AutorResponseDTO findById(Integer codAu) throws RegistroNaoEncontradoException {
        Autor autor = getAutorOrThrowException(codAu);

        return new AutorResponseDTO(autor);
    }

    public AutorResponseDTO update(AutorUpdateRequestDTO request) {
        Autor autor = getAutorOrThrowException(request.codAu());

        autor.setNome(request.nome());

        if (request.livrosId() != null) {
            if (request.livrosId().isEmpty()) {
                excluirLivros(autor);
            } else {
                atualizarLivros(autor, request.livrosId());
            }
        }

        return new AutorResponseDTO(autorRepository.save(autor));
    }

    public void delete(Integer codAu) throws RegistroNaoEncontradoException {
        Autor autor = getAutorOrThrowException(codAu);

        livroAutorService.findByAutor(autor)
                .forEach(livroAutor -> livroAutorService.delete(livroAutor));

        autorRepository.delete(autor);
    }

    public List<LivroResponseDTO> getLivros(Integer codAu) {
        Autor autor = getAutorOrThrowException(codAu);

        return livroAutorService.findByAutor(autor)
                .stream()
                .map(LivroAutor::getLivro)
                .map(LivroResponseDTO::new)
                .toList();
    }

    public Autor getAutorOrThrowException(Integer codAu) {
        return autorRepository.findById(codAu)
                .orElseThrow(() -> new RegistroNaoEncontradoException(codAu));
    }

    public List<AutorResponseDTO> findByNome(String nome) {
        return autorRepository.findByNomeIgnoreCaseContaining(nome)
                .stream()
                .map(AutorResponseDTO::new)
                .toList();
    }

    private void atualizarLivros(Autor autor, List<Integer> livroRequestList) {

        Set<Integer> livrosAtuais = livroAutorService.findByAutor(autor)
                .stream()
                .map(LivroAutor::getLivro)
                .map(Livro::getCodL)
                .collect(Collectors.toSet());

        Set<Integer> livrosRequest = new HashSet<>(livroRequestList);

        // Livros para excluir
        Set<Integer> livrosExclusao = livrosAtuais.stream()
                .filter(id -> !livrosRequest.contains(id))
                .collect(Collectors.toSet());

        for (Integer codL : livrosExclusao) {
            livroAutorService.deleteByLivroAndAutor(codL, autor.getCodAu());
        }

        // Livros para incluir
        Set<Integer> livrosInclusao = livrosRequest.stream()
                .filter(id -> !livrosAtuais.contains(id))
                .collect(Collectors.toSet());

        for (Integer codL : livrosInclusao) {
            Livro livro = livroAutorService.getByLivroCodL(codL);
            livroAutorService.save(new LivroAutor(livro, autor));
        }
    }

    private void excluirLivros(Autor autor) {
        livroAutorService.findByAutor(autor)
                .forEach(livroAutor -> livroAutorService.delete(livroAutor));
    }
}
