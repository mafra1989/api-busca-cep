package br.com.mazzatech.domain.strategy;

import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class CepStrategyTest {

    @InjectMocks
    private CepStrategy cepStrategy;

    @Mock
    @Qualifier("brasilApiAdapter")
    private ConsultaExternaOutPort brasilApiConsultaExternaOutPort;

    @Mock
    @Qualifier("cdnApiAdapter")
    private ConsultaExternaOutPort cdnApiConsultaExternaOutPort;

    @Mock
    @Qualifier("viaCepAdapter")
    private ConsultaExternaOutPort viaCepConsultaExternaOutPort;

    @Test
    public void deveExecutarStrategyComSucesso() {
        assertDoesNotThrow(() -> {
            cepStrategy.consultaCep(69078150L);
        });
    }
}
