package br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErroResponse {

    private String message;

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - hh:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();

    private List<ApiSubErroResponse> errors;

    private String developerMessage;

    public ApiErroResponse(String message, HttpStatus status, Throwable ex) {
        this.message = message;
        this.status = status;
        this.developerMessage = ex.getLocalizedMessage();
    }

    public ApiErroResponse(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Erro inesperado.";
        this.developerMessage = ex.getLocalizedMessage();
    }

    public ApiErroResponse(HttpStatus status) {
        this.status = status;
    }

    private void addSubErro(ApiSubErroResponse apiSubErroResponse) {
        if (errors == null){
            errors = new ArrayList<>();
        }
        errors.add(apiSubErroResponse);
    }

    public void addErroNegocio(String codigo, String message) {
        addSubErro(new SubErroNegocio(codigo, message));
    }

    private void addValidacaoErro(String object, String field, Object rejectedValue, String message) {
        addSubErro(new ApiValidacaoErroResponse(object, field, message, rejectedValue));
    }

    private void addValidacaoErro(String object, String message) {
        addSubErro(new ApiValidacaoErroResponse(object, message));
    }

    private void addValidacaoErro(FieldError fieldError) {
        this.addValidacaoErro(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    private void addValidacaoErro(ObjectError objectError) {
        this.addValidacaoErro(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidacaoErro(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidacaoErro);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidacaoErro);
    }

}
