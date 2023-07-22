package br.com.mazzatech.domain.port.input;

import br.com.mazzatech.domain.model.CepDomain;

public interface ConsultaExternaInPort {

    CepDomain consultaCep(Long code);
}
