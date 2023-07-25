package br.com.mazzatech.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public BusinessException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}