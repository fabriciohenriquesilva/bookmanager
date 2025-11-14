package br.com.bookmanager.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(Number id) {
        super("Não foi encontrado um registro com código = " + id);
    }

    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}