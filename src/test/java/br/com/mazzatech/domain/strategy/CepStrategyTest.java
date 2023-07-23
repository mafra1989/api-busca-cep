package br.com.mazzatech.domain.strategy;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.FixtureLoader;
import utils.fixtures.domain.CepDomainFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CepStrategyTest {

    @InjectMocks
    private CepStrategy cepStrategy;

    @Mock
    private ConsultaExternaOutPort consultaExternaOutPort;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveExecutarStrategyComSucesso() {
        CepDomain cepDomainMock = Fixture.from(CepDomain.class)
                .gimme(CepDomainFixture.VALIDO);

        when(consultaExternaOutPort.consultaCep(any())).thenReturn(cepDomainMock);

        assertDoesNotThrow(() -> {
            CepDomain cepDomain = cepStrategy.consultaCep(69078150L);

            assertNotNull(cepDomain);
            assertEquals(cepDomainMock.getCidade(), cepDomain.getCidade());

            verify(consultaExternaOutPort, times(3)).consultaCep(any());
        });
    }
}
