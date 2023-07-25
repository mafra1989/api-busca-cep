package br.com.mazzatech.infrastructure.adapter.input.rest.exception.handler;

import br.com.mazzatech.domain.exception.CepFeignException;
import br.com.mazzatech.domain.exception.CepNaoEncontradoException;
import br.com.mazzatech.domain.model.enumerators.MensagensNegociosEnum;
import br.com.mazzatech.infrastructure.adapter.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro.ApiErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(CepNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErroResponse handleCepNaoEncontradoException(CepNaoEncontradoException ex) {

        ApiErroResponse error = new ApiErroResponse(ex.getErrorMessage(), HttpStatus.NOT_FOUND, ex);
        return error;
    }

    @ExceptionHandler(CepFeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErroResponse handleCepFeignException(CepFeignException ex) {

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem().concat(" Tente novamente."), HttpStatus.INTERNAL_SERVER_ERROR, ex);

        var messages = ex.getErrorMessage().split("/");
        for (String message : messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        return error;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErroResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);
        error.addErroNegocio(MensagensNegociosEnum.CEP_INVALIDO.getCodigo(), MensagensNegociosEnum.CEP_INVALIDO.getMensagem());

        return error;
    }
}
