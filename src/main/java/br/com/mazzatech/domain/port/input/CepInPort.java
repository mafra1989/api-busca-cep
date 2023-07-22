package br.com.mazzatech.domain.port.input;

import br.com.mazzatech.domain.model.CepDomain;

public interface CepInPort {

    CepDomain consultarCep(Long code);
}
