package br.com.fiap.calorias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AlimentoNaoEncontradoException extends RuntimeException {

    public AlimentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
