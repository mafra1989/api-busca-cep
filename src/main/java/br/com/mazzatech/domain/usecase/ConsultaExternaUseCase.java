package br.com.mazzatech.domain.usecase;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.input.ConsultaExternaInPort;
import br.com.mazzatech.domain.strategy.CepStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ConsultaExternaUseCase implements ConsultaExternaInPort {

    @Autowired
    private CepStrategy cepStrategy;

    @Override
    public CepDomain consultaCep(Long code) throws ExecutionException, InterruptedException {
        return cepStrategy.consultaCep(code);
    }
}
