package br.com.bookmanager.domain.assunto;

import br.com.bookmanager.domain.assunto.dto.AssuntoCreateRequestDTO;
import br.com.bookmanager.domain.assunto.dto.AssuntoResponseDTO;
import br.com.bookmanager.domain.assunto.dto.AssuntoUpdateRequestDTO;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("assunto")
public class AssuntoController {

    @Autowired
    private AssuntoService assuntoService;

    @PostMapping
    public ResponseEntity<AssuntoResponseDTO> create(@Valid @RequestBody AssuntoCreateRequestDTO request) {
        AssuntoResponseDTO assunto = assuntoService.create(request);
        URI location = URI.create("/assunto/" + assunto.codAs());
        return ResponseEntity.created(location).body(assunto);
    }

    @GetMapping
    public ResponseEntity<Page<AssuntoResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(assuntoService.findAll(pageable));
    }

    @GetMapping("/{codAs}")
    public ResponseEntity<AssuntoResponseDTO> findById(@PathVariable("codAs") Integer codAs) {
        return ResponseEntity.ok(assuntoService.findById(codAs));
    }

    @PutMapping
    public ResponseEntity<AssuntoResponseDTO> update(@Valid @RequestBody AssuntoUpdateRequestDTO request) {
        return ResponseEntity.ok(assuntoService.update(request));
    }

    @DeleteMapping("/{codAs}")
    public ResponseEntity<Void> delete(@PathVariable("codAs") Integer codAs) {
        assuntoService.delete(codAs);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codAs}/livros")
    public ResponseEntity<List<LivroResponseDTO>> getLivrosByAssunto(@PathVariable("codAs") Integer codAs) {
        return ResponseEntity.ok(assuntoService.getLivros(codAs));
    }

}
