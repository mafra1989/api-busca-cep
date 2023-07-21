package br.com.mazzatech.domain.port.input;

import br.com.mazzatech.domain.model.Cep;

public interface ConsultaExternaInPort {

    Cep consultaCep(Long code);
}
