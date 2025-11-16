package br.com.bookmanager.domain.livro;

import br.com.bookmanager.core.ReportService;
import br.com.bookmanager.domain.assunto.dto.AssuntoResponseDTO;
import br.com.bookmanager.domain.assunto.model.Assunto;
import br.com.bookmanager.domain.autor.dto.AutorResponseDTO;
import br.com.bookmanager.domain.autor.model.Autor;
import br.com.bookmanager.domain.livro.dto.LivroCreateRequestDTO;
import br.com.bookmanager.domain.livro.dto.LivroReportDTO;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroAssuntoService livroAssuntoService;

    @Autowired
    private LivroAutorService livroAutorService;

    @Autowired
    private ReportService reportService;

    public LivroResponseDTO create(LivroCreateRequestDTO request) {
        Livro entity = request.toEntity();

        Livro livro = livroRepository.save(entity);

        if (request.autoresId() != null && !request.autoresId().isEmpty()) {
            for (Integer autorId : request.autoresId()) {
                Autor autor = livroAutorService.getByAutorCodAu(autorId);
                livroAutorService.save(new LivroAutor(livro, autor));
            }
        }

        if (request.assuntosId() != null && !request.assuntosId().isEmpty()) {
            for (Integer assuntoId : request.assuntosId()) {
                Assunto assunto = livroAssuntoService.getByAssuntoCodAs(assuntoId);
                livroAssuntoService.save(new LivroAssunto(livro, assunto));
            }
        }

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

        if (request.autoresId() != null) {
            if (request.autoresId().isEmpty()) {
                excluirAutores(livro);
            } else {
                atualizarAutores(livro, request.autoresId());
            }
        }

        if (request.assuntosId() != null) {
            if (request.assuntosId().isEmpty()) {
                excluirAssuntos(livro);
            } else {
                atualizarAssuntos(livro, request.assuntosId());
            }
        }

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

    private void atualizarAutores(Livro livro, List<Integer> autorRequestList) {

        Set<Integer> autoresAtuais = livroAutorService.findByLivro(livro)
                .stream()
                .map(LivroAutor::getAutor)
                .map(Autor::getCodAu)
                .collect(Collectors.toSet());

        Set<Integer> autoresRequest = new HashSet<>(autorRequestList);

        // Autores para excluir
        Set<Integer> autoresExclusao = autoresAtuais.stream()
                .filter(id -> !autoresRequest.contains(id))
                .collect(Collectors.toSet());

        for (Integer codAu : autoresExclusao) {
            livroAutorService.deleteByLivroAndAutor(livro.getCodL(), codAu);
        }

        // Autores para incluir
        Set<Integer> autoresInclusao = autoresRequest.stream()
                .filter(id -> !autoresAtuais.contains(id))
                .collect(Collectors.toSet());

        for (Integer codAu : autoresInclusao) {
            Autor autor = livroAutorService.getByAutorCodAu(codAu);
            livroAutorService.save(new LivroAutor(livro, autor));
        }
    }

    private void excluirAutores(Livro livro) {
        livroAutorService.findByLivro(livro)
                .forEach(livroAutor -> livroAutorService.delete(livroAutor));
    }

    private void atualizarAssuntos(Livro livro, List<Integer> assuntoRequestList) {

        Set<Integer> assuntosAtuais = livroAssuntoService.findByLivro(livro)
                .stream()
                .map(LivroAssunto::getAssunto)
                .map(Assunto::getCodAs)
                .collect(Collectors.toSet());

        Set<Integer> assuntosRequest = new HashSet<>(assuntoRequestList);

        // Assuntos para excluir
        Set<Integer> assuntosExclusao = assuntosAtuais.stream()
                .filter(id -> !assuntosRequest.contains(id))
                .collect(Collectors.toSet());

        for (Integer codAs : assuntosExclusao) {
            livroAssuntoService.deleteByLivroAndAssunto(livro.getCodL(), codAs);
        }

        // Assuntos para incluir
        Set<Integer> assuntosInclusao = assuntosRequest.stream()
                .filter(id -> !assuntosAtuais.contains(id))
                .collect(Collectors.toSet());

        for (Integer codAs : assuntosInclusao) {
            Assunto assunto = livroAssuntoService.getByAssuntoCodAs(codAs);
            livroAssuntoService.save(new LivroAssunto(livro, assunto));
        }
    }

    private void excluirAssuntos(Livro livro) {
        livroAssuntoService.findByLivro(livro)
                .forEach(livroAssunto -> livroAssuntoService.delete(livroAssunto));
    }

    public byte[] relatorio() {
        List<LivroReportDTO> dataSource = livroRepository.relatorio();

        String reportPath = "/reports/livros/livros.jrxml";

        return this.reportService.createReport(reportPath, new HashMap<>(), dataSource);
    }
}
