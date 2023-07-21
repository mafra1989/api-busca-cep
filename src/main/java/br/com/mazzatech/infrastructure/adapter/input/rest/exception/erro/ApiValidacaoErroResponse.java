package br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiValidacaoErroResponse implements ApiSubErroResponse {

    private String objeto;
    private String mensagem;
    private String campo;
    private Object valorRejeitado;

    public ApiValidacaoErroResponse(String objeto, String mensagem) {
        this.objeto = objeto;
        this.mensagem = mensagem;
    }

}
