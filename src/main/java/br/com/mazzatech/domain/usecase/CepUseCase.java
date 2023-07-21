package br.com.mazzatech.domain.usecase;

import br.com.mazzatech.domain.port.input.ConsultaExternaInPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mazzatech.domain.model.Cep;
import br.com.mazzatech.domain.port.input.CepInPort;

@Component
public class CepUseCase implements CepInPort {

    @Autowired
    ConsultaExternaInPort consultaExternaInPort;

    @Override
    public Cep consultarCep(Long code) {
        return consultaExternaInPort.consultaCep(code);
    }
}
