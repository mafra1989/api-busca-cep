package br.com.mazzatech.domain.exception;

public class CepFeignException extends BusinessException {

    public CepFeignException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
