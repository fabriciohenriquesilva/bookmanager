package br.com.bookmanager.domain.autor;

import br.com.bookmanager.domain.autor.dto.AutorCreateRequestDTO;
import br.com.bookmanager.domain.autor.dto.AutorResponseDTO;
import br.com.bookmanager.domain.autor.dto.AutorUpdateRequestDTO;
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
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> create(@Valid @RequestBody AutorCreateRequestDTO request) {
        AutorResponseDTO autor = autorService.create(request);
        URI location = URI.create("/autores/" + autor.codAu());
        return ResponseEntity.created(location).body(autor);
    }

    @GetMapping
    public ResponseEntity<Page<AutorResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(autorService.findAll(pageable));
    }

    @GetMapping("/{codAu}")
    public ResponseEntity<AutorResponseDTO> findById(@PathVariable("codAu") Integer codAu) {
        return ResponseEntity.ok(autorService.findById(codAu));
    }

    @PutMapping
    public ResponseEntity<AutorResponseDTO> update(@Valid @RequestBody AutorUpdateRequestDTO request) {
        return ResponseEntity.ok(autorService.update(request));
    }

    @DeleteMapping("/{codAu}")
    public ResponseEntity<Void> delete(@PathVariable("codAu") Integer codAu) {
        autorService.delete(codAu);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codAu}/livros")
    public ResponseEntity<List<LivroResponseDTO>> getLivrosByAutor(@PathVariable("codAu") Integer codAu) {
        return ResponseEntity.ok(autorService.getLivros(codAu));
    }

}
