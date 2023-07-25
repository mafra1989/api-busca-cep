package br.com.mazzatech.domain.exception;

public class CepNaoEncontradoException extends BusinessException {

    public CepNaoEncontradoException(String errorMessage) {
        super(errorMessage);
    }
}
