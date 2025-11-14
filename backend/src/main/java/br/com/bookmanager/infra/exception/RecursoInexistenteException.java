package br.com.bookmanager.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoInexistenteException extends RuntimeException {

    public RecursoInexistenteException(Number id) {
        super("não foi encontrado um registro com código = " + id);
    }

    public RecursoInexistenteException(String message) {
        super(message);
    }
}