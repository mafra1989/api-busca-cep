package br.com.mazzatech.domain.model.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensNegociosEnum {

    DADOS_NAO_ENCONTRADO("10","Dados não encontrado.");

    private final String codigo;
    private final String mensagem;

}
