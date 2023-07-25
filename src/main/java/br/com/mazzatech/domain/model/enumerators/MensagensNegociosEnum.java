package br.com.mazzatech.domain.model.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensNegociosEnum {

    CEP_NAO_ENCONTRADO("10","Cep não encontrado."),
    CEP_INVALIDO("20","O formato do cep não é válido."),
    ERRO_INTERNO("30","Erro na chamada do serviço externo.");

    private final String codigo;
    private final String mensagem;

}
