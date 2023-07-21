package br.com.mazzatech.domain.exception;

public class BusinessException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public BusinessException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}