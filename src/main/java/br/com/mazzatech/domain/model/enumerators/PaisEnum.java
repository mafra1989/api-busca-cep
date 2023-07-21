package br.com.mazzatech.domain.model.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaisEnum {

    BRASIL("Brasil");

    private final String nome;
}
