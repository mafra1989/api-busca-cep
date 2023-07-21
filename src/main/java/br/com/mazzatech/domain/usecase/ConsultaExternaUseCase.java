package br.com.mazzatech.domain.usecase;

import br.com.mazzatech.domain.model.Cep;
import br.com.mazzatech.domain.port.input.ConsultaExternaInPort;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConsultaExternaUseCase implements ConsultaExternaInPort {

    @Autowired
    @Qualifier("brasilApiAdapter")
    ConsultaExternaOutPort consultaExternaOutPort;

    @Override
    public Cep consultaCep(Long code) {
        return consultaExternaOutPort.consultaCep(code);
    }
}
