package br.com.mazzatech.domain.port.output;

import br.com.mazzatech.domain.model.CepDomain;

public interface ConsultaExternaOutPort {

    CepDomain consultaCep(Long code);

}
