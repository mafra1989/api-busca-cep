package br.com.mazzatech.infrastructure.adapter.input.rest.exception.enumarator;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagenInfraestruturaEnum {

    ERRO_INTERNO("Erro interno.");

    private final String mensagem;

}