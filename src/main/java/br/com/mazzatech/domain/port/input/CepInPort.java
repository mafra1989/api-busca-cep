package br.com.mazzatech.domain.port.input;

import br.com.mazzatech.domain.model.Cep;

public interface CepInPort {

    Cep consultarCep(Long code);
}
