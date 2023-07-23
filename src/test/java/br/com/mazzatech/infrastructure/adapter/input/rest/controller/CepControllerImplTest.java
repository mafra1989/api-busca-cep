package br.com.mazzatech.infrastructure.adapter.input.rest.controller;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.input.CepInPort;
import br.com.mazzatech.infrastructure.adapter.input.rest.dto.response.CepDtoResponse;
import br.com.mazzatech.infrastructure.adapter.input.rest.mapper.CepInputMapper;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.FixtureLoader;
import utils.fixtures.domain.CepDomainFixture;
import utils.fixtures.dto.CepDtoResponseFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CepControllerImplTest {

    @InjectMocks
    private CepControllerImpl cepControllerImpl;

    @Mock
    private CepInPort cepInPort;

    @Mock
    private CepInputMapper mapper;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveConsultarCEPComSucesso() {
        CepDomain cepDomain = Fixture.from(CepDomain.class).gimme(CepDomainFixture.VALIDO);
        CepDtoResponse cepDtoResponse = Fixture.from(CepDtoResponse.class).gimme(CepDtoResponseFixture.VALIDO);

        when(cepInPort.consultarCep(any())).thenReturn(cepDomain);
        when(mapper.toResponseDto(any())).thenReturn(cepDtoResponse);

        assertDoesNotThrow(() -> {
            ResponseEntity<CepDtoResponse> response = cepControllerImpl.consultarCEP(69078150L);

            assertNotNull(response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(ResponseEntity.class, response.getClass());
        });
    }
}
