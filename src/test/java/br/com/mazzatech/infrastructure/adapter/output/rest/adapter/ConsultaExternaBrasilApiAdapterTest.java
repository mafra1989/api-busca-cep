package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.config.ProfileConfiguration;
import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.URIEnum;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.BrasilApiResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepRestTemplateService;
import br.com.six2six.fixturefactory.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.FixtureLoader;
import utils.fixtures.domain.CepDomainFixture;
import utils.fixtures.entity.BrasilApiResponseEntityFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaExternaBrasilApiAdapterTest {

    @InjectMocks
    private ConsultaExternaBrasilApiAdapter consultaExternaBrasilApiAdapter;

    @Mock
    private CepRestTemplateService<Object, BrasilApiResponseEntity> brasilApiRestTemplateService;

    @Mock
    private ProfileConfiguration profile;

    @Spy
    private CepOutputMapper mapper = Mappers.getMapper(CepOutputMapper.class);

    @Mock
    private ObjectMapper objectMapper;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveConsultarCepComSucesso() {
        BrasilApiResponseEntity brasilApiResponseEntityMock = Fixture.from(BrasilApiResponseEntity.class)
                        .gimme(BrasilApiResponseEntityFixture.VALIDO);
        CepDomain cepDomainMock = Fixture.from(CepDomain.class)
                .gimme(CepDomainFixture.VALIDO);

        when(profile.getUriBrasilApi()).thenReturn(URIEnum.BRASIL_API.getNome());
        when(brasilApiRestTemplateService.get(any(), any())).thenReturn(brasilApiResponseEntityMock);

        assertDoesNotThrow(() -> {
            CepDomain cepDomain = consultaExternaBrasilApiAdapter.consultaCep(69078150L);

            assertEquals(cepDomainMock.getCidade(), cepDomain.getCidade());
            assertEquals(cepDomainMock.getEstado(), cepDomain.getEstado());
        });
    }
}
