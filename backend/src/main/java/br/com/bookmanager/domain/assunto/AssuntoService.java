package br.com.bookmanager.domain.assunto;

import br.com.bookmanager.domain.assunto.dto.AssuntoCreateRequestDTO;
import br.com.bookmanager.domain.assunto.dto.AssuntoResponseDTO;
import br.com.bookmanager.domain.assunto.dto.AssuntoUpdateRequestDTO;
import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import br.com.bookmanager.domain.livroassunto.LivroAssuntoService;
import br.com.bookmanager.domain.livroassunto.model.LivroAssunto;
import br.com.bookmanager.infra.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Autowired
    private LivroAssuntoService livroAssuntoService;

    public AssuntoResponseDTO create(AssuntoCreateRequestDTO request) {
        Assunto entity = request.toEntity();

        Assunto assunto = assuntoRepository.save(entity);

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

}
