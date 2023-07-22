package br.com.mazzatech.domain.port.input;

import br.com.mazzatech.domain.model.CepDomain;

import java.util.concurrent.ExecutionException;

public interface ConsultaExternaInPort {

    CepDomain consultaCep(Long code) throws ExecutionException, InterruptedException;
}
