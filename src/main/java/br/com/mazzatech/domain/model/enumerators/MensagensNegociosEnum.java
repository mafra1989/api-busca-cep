package br.com.mazzatech.domain.model.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensNegociosEnum {

    CEP_NAO_ENCONTRADO("10","Cep não encontrado.");

    private final String codigo;
    private final String mensagem;

}
