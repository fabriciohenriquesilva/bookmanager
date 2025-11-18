package br.com.bookmanager.domain.assunto;

import br.com.bookmanager.domain.assunto.dto.AssuntoCreateRequestDTO;
import br.com.bookmanager.domain.assunto.dto.AssuntoResponseDTO;
import br.com.bookmanager.domain.assunto.dto.AssuntoUpdateRequestDTO;
import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import br.com.bookmanager.domain.livro.model.Livro;
import br.com.bookmanager.domain.livroassunto.LivroAssuntoService;
import br.com.bookmanager.domain.livroassunto.model.LivroAssunto;
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
public class AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Autowired
    private LivroAssuntoService livroAssuntoService;

    public AssuntoResponseDTO create(AssuntoCreateRequestDTO request) {
        Assunto entity = request.toEntity();

        Assunto assunto = assuntoRepository.save(entity);

        if (request.livrosId() != null && !request.livrosId().isEmpty()) {
            for (Integer livroId : request.livrosId()) {
                Livro livro = livroAssuntoService.getByLivroCodL(livroId);
                livroAssuntoService.save(new LivroAssunto(livro, assunto));
            }
        }

        return new AssuntoResponseDTO(assunto);
    }

    public Page<AssuntoResponseDTO> findAll(Pageable pageable) {
        return assuntoRepository.findAll(pageable)
                .map(AssuntoResponseDTO::new);
    }

    public AssuntoResponseDTO findById(Integer codAs) throws RegistroNaoEncontradoException {
        Assunto assunto = getAssuntoOrThrowException(codAs);

        return new AssuntoResponseDTO(assunto);
    }

    public AssuntoResponseDTO update(AssuntoUpdateRequestDTO request) {
        Assunto assunto = getAssuntoOrThrowException(request.codAs());

        assunto.setDescricao(request.descricao());

        if (request.livrosId() != null) {
            if (request.livrosId().isEmpty()) {
                excluirLivros(assunto);
            } else {
                atualizarLivros(assunto, request.livrosId());
            }
        }

        return new AssuntoResponseDTO(assuntoRepository.save(assunto));
    }

    public void delete(Integer codAs) throws RegistroNaoEncontradoException {
        Assunto assunto = getAssuntoOrThrowException(codAs);

        livroAssuntoService.findByAssunto(assunto)
                .forEach(livroAssunto -> livroAssuntoService.delete(livroAssunto));

        assuntoRepository.delete(assunto);
    }

    public List<LivroResponseDTO> getLivros(Integer codAs) {
        Assunto assunto = getAssuntoOrThrowException(codAs);

        return livroAssuntoService.findByAssunto(assunto)
                .stream()
                .map(LivroAssunto::getLivro)
                .map(LivroResponseDTO::new)
                .toList();
    }

    public Assunto getAssuntoOrThrowException(Integer codAs) {
        return assuntoRepository.findById(codAs)
                .orElseThrow(() -> new RegistroNaoEncontradoException(codAs));
    }

    public List<AssuntoResponseDTO> findByNome(String descricao) {
        return assuntoRepository.findByDescricaoIgnoreCaseContaining(descricao)
                .stream()
                .map(AssuntoResponseDTO::new)
                .toList();
    }

    private void atualizarLivros(Assunto assunto, List<Integer> livroRequestList) {

        Set<Integer> livrosAtuais = livroAssuntoService.findByAssunto(assunto)
                .stream()
                .map(LivroAssunto::getLivro)
                .map(Livro::getCodL)
                .collect(Collectors.toSet());

        Set<Integer> livrosRequest = new HashSet<>(livroRequestList);

        // Livros para excluir
        Set<Integer> livrosExclusao = livrosAtuais.stream()
                .filter(id -> !livrosRequest.contains(id))
                .collect(Collectors.toSet());

        for (Integer codL : livrosExclusao) {
            livroAssuntoService.deleteByLivroAndAssunto(codL, assunto.getCodAs());
        }

        // Livros para incluir
        Set<Integer> livrosInclusao = livrosRequest.stream()
                .filter(id -> !livrosAtuais.contains(id))
                .collect(Collectors.toSet());

        for (Integer codL : livrosInclusao) {
            Livro livro = livroAssuntoService.getByLivroCodL(codL);
            livroAssuntoService.save(new LivroAssunto(livro, assunto));
        }
    }

    private void excluirLivros(Assunto assunto) {
        livroAssuntoService.findByAssunto(assunto)
                .forEach(livroAssunto -> livroAssuntoService.delete(livroAssunto));
    }
}
