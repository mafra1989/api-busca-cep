package br.com.mazzatech.domain.usecase;

import br.com.mazzatech.domain.strategy.CepStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class ConsultaExternaUseCaseTest {

    @InjectMocks
    private ConsultaExternaUseCase consultaExternaUseCase;

    @Mock
    private CepStrategy cepStrategy;

    @Test
    public void deveConsultarCepComSucesso() {
        assertDoesNotThrow(() -> {
            consultaExternaUseCase.consultaCep(69078150L);
        });
    }
}
