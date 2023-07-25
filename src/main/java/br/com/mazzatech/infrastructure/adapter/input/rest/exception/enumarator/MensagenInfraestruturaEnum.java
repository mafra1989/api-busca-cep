package br.com.mazzatech.infrastructure.adapter.input.rest.exception.enumarator;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagenInfraestruturaEnum {

    FALHA_PROCESSAMENTO("Falha no processamento da requisição.");

    private final String mensagem;

}