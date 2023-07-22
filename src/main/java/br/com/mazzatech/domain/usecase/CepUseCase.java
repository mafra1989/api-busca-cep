package br.com.mazzatech.domain.usecase;

import br.com.mazzatech.domain.port.input.ConsultaExternaInPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.input.CepInPort;

import java.util.concurrent.ExecutionException;

@Component
public class CepUseCase implements CepInPort {

    @Autowired
    ConsultaExternaInPort consultaExternaInPort;

    @Override
    public CepDomain consultarCep(Long code) {

        try {
            return consultaExternaInPort.consultaCep(code);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
