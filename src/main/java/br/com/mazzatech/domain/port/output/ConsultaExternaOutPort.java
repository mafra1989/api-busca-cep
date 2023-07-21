package br.com.mazzatech.domain.port.output;

import br.com.mazzatech.domain.model.Cep;

public interface ConsultaExternaOutPort {

    Cep consultaCep(Long code);

}
