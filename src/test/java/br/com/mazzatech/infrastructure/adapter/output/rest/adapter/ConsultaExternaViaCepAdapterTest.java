package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.config.ProfileConfiguration;
import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.URIEnum;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.ViaCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepRestTemplateService;
import br.com.six2six.fixturefactory.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.FixtureLoader;
import utils.fixtures.domain.CepDomainFixture;
import utils.fixtures.entity.ViaCepResponseEntityFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaExternaViaCepAdapterTest {

    @InjectMocks
    private ConsultaExternaViaCepAdapter consultaExternaViaCepAdapter;

    @Mock
    private CepRestTemplateService<Object, ViaCepResponseEntity> viaCepRestTemplateService;

    @Mock
    private ProfileConfiguration profile;

    @Mock
    private CepOutputMapper mapper;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveConsultarCepComSucesso() {
        ViaCepResponseEntity viaCepResponseEntityMock = Fixture.from(ViaCepResponseEntity.class)
                        .gimme(ViaCepResponseEntityFixture.VALIDO);
        CepDomain cepDomainMock = Fixture.from(CepDomain.class)
                .gimme(CepDomainFixture.VALIDO);

        when(profile.getUriViaCep()).thenReturn(URIEnum.VIA_CEPAPI.getNome());
        when(viaCepRestTemplateService.get(any(), any())).thenReturn(viaCepResponseEntityMock);
        when(mapper.viaCeptoDomain(any())).thenReturn(cepDomainMock);

        assertDoesNotThrow(() -> {
            CepDomain cepDomain = consultaExternaViaCepAdapter.consultaCep(69078150L);

            assertEquals(cepDomainMock.getCidade(), cepDomain.getCidade());
            assertEquals(cepDomainMock.getIbgeCidade(), cepDomain.getIbgeCidade());
        });
    }
}
