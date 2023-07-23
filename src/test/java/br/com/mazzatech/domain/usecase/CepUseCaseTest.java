package br.com.mazzatech.domain.usecase;

import br.com.mazzatech.domain.port.input.ConsultaExternaInPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class CepUseCaseTest {

    @InjectMocks
    private CepUseCase cepUseCase;

    @Mock
    private ConsultaExternaInPort consultaExternaInPort;

    @Test
    public void deveConsultarCepComSucesso() {
        assertDoesNotThrow(() -> {
            cepUseCase.consultarCep(69078150L);
        });
    }
}
