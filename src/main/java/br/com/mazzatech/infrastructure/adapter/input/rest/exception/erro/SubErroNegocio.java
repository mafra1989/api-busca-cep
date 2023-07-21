package br.com.mazzatech.infrastructure.adapter.input.rest.exception.erro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubErroNegocio implements ApiSubErroResponse {

        private final String codigo;
        private final String mensagem;

}
