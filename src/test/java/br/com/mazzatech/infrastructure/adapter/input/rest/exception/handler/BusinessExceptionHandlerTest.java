package br.com.mazzatech.infrastructure.adapter.input.rest.exception.handler;

import br.com.mazzatech.domain.exception.CepFeignException;
import br.com.mazzatech.domain.exception.CepNaoEncontradoException;
import br.com.mazzatech.domain.model.enumerators.MensagensNegociosEnum;
import br.com.mazzatech.infrastructure.adapter.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro.ApiErroResponse;
import br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro.ApiSubErroResponse;
import br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro.SubErroNegocio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class BusinessExceptionHandlerTest {

    @InjectMocks
    private BusinessExceptionHandler exceptionHandler;

    @Test
    public void deveMontarCepNaoEncontradoExceptionComSucesso() {
        CepNaoEncontradoException ex = new CepNaoEncontradoException(MensagensNegociosEnum.CEP_NAO_ENCONTRADO.getMensagem());
        ApiErroResponse response = exceptionHandler.handleCepNaoEncontradoException(ex);

        HttpStatus statusHttpEsperado = HttpStatus.NOT_FOUND;
        String mensagemEsperada = MensagensNegociosEnum.CEP_NAO_ENCONTRADO.getMensagem();

        assertEquals(statusHttpEsperado, response.getStatus());
        assertEquals(mensagemEsperada, Objects.requireNonNull(response.getMessage()));
    }

    @Test
    public void deveMontarCepFeignExceptionComSucesso() {
        CepFeignException ex = new CepFeignException(MensagensNegociosEnum.ERRO_INTERNO.getCodigo(), MensagensNegociosEnum.ERRO_INTERNO.getMensagem());
        ApiErroResponse response = exceptionHandler.handleCepFeignException(ex);

        HttpStatus statusHttpEsperado = HttpStatus.INTERNAL_SERVER_ERROR;
        String mensagemEsperada = MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem().concat(" Tente novamente.");
        String codigoEsperado = ex.getErrorCode();
        String mensagemErroEsperada = ex.getErrorMessage();

        Optional<ApiSubErroResponse> erro = response.getErrors().stream().findFirst();
        if(erro.isPresent()) {
            assertEquals(codigoEsperado, ((SubErroNegocio) erro.get()).getCodigo());
            assertEquals(mensagemErroEsperada, ((SubErroNegocio) erro.get()).getMensagem());
        }

        assertEquals(statusHttpEsperado, response.getStatus());
        assertEquals(mensagemEsperada, Objects.requireNonNull(response.getMessage()));
    }

    @Test
    public void deveMontarMethodArgumentTypeMismatchExceptionComSucesso() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        ApiErroResponse response = exceptionHandler.handleMethodArgumentTypeMismatchException(ex);

        HttpStatus statusHttpEsperado = HttpStatus.BAD_REQUEST;
        String mensagemEsperada = MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem();
        String codigoEsperado = MensagensNegociosEnum.CEP_INVALIDO.getCodigo();
        String mensagemErroEsperada = MensagensNegociosEnum.CEP_INVALIDO.getMensagem();

        Optional<ApiSubErroResponse> erro = response.getErrors().stream().findFirst();
        if(erro.isPresent()) {
            assertEquals(codigoEsperado, ((SubErroNegocio) erro.get()).getCodigo());
            assertEquals(mensagemErroEsperada, ((SubErroNegocio) erro.get()).getMensagem());
        }

        assertEquals(statusHttpEsperado, response.getStatus());
        assertEquals(mensagemEsperada, Objects.requireNonNull(response.getMessage()));
    }
}
