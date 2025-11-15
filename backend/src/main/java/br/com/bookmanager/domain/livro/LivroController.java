package br.com.bookmanager.domain.livro;

import br.com.bookmanager.domain.assunto.dto.AssuntoResponseDTO;
import br.com.bookmanager.domain.autor.dto.AutorResponseDTO;
import br.com.bookmanager.domain.livro.dto.LivroCreateRequestDTO;
import br.com.bookmanager.domain.livro.dto.LivroResponseDTO;
import br.com.bookmanager.domain.livro.dto.LivroUpdateRequestDTO;
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
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroResponseDTO> create(@Valid @RequestBody LivroCreateRequestDTO request) {
        LivroResponseDTO assunto = livroService.create(request);
        URI location = URI.create("/livros/" + assunto.codL());
        return ResponseEntity.created(location).body(assunto);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(livroService.findAll(pageable));
    }

    @GetMapping("/{codL}")
    public ResponseEntity<LivroResponseDTO> findById(@PathVariable("codL") Integer codL) {
        return ResponseEntity.ok(livroService.findById(codL));
    }

    @PutMapping
    public ResponseEntity<LivroResponseDTO> update(@Valid @RequestBody LivroUpdateRequestDTO request) {
        return ResponseEntity.ok(livroService.update(request));
    }

    @DeleteMapping("/{codL}")
    public ResponseEntity<Void> delete(@PathVariable("codL") Integer codL) {
        livroService.delete(codL);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codL}/assuntos")
    public ResponseEntity<List<AssuntoResponseDTO>> getAssuntosByLivro(@PathVariable("codL") Integer codL) {
        return ResponseEntity.ok(livroService.getAssuntos(codL));
    }

    @GetMapping("/{codL}/autores")
    public ResponseEntity<List<AutorResponseDTO>> getAutoresByLivro(@PathVariable("codL") Integer codL) {
        return ResponseEntity.ok(livroService.getAutores(codL));
    }
}
