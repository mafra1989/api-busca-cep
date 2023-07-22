package br.com.mazzatech.domain.model.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum URIEnum {

    VIA_CEPAPI("https://viacep.com.br/ws/"),
    CDN_CEP("https://cdn.apicep.com/file/apicep/"),
    BRASIL_API("https://brasilapi.com.br/api/cep/v1/");

    private final String nome;
}
