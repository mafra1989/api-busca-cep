package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.config.ProfileConfiguration;
import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.URIEnum;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepFeignClientService;
import br.com.six2six.fixturefactory.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.FixtureLoader;
import utils.fixtures.domain.CepDomainFixture;
import utils.fixtures.entity.CdnCepResponseEntityFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaExternaCdnApiAdapterTest {

    @InjectMocks
    private ConsultaExternaCdnApiAdapter consultaExternaCdnApiAdapter;

    @Mock
    private CepFeignClientService cepFeignClientService;

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
        CdnCepResponseEntity cdnCepResponseEntityMock = Fixture.from(CdnCepResponseEntity.class)
                        .gimme(CdnCepResponseEntityFixture.VALIDO);
        CepDomain cepDomainMock = Fixture.from(CepDomain.class)
                .gimme(CepDomainFixture.VALIDO);

        ResponseEntity<CdnCepResponseEntity> responseEntity = new ResponseEntity<>(
                cdnCepResponseEntityMock,
                new HttpHeaders(),
                HttpStatus.OK
        );

        when(profile.getUriCdnCep()).thenReturn(URIEnum.CDN_CEP.getNome());
        when(cepFeignClientService.buscaCep(any())).thenReturn(responseEntity);
        when(mapper.cdnCeptoDomain(any())).thenReturn(cepDomainMock);

        assertDoesNotThrow(() -> {
            CepDomain cepDomain = consultaExternaCdnApiAdapter.consultaCep(69078150L);

            assertEquals(cepDomainMock.getCidade(), cepDomain.getCidade());
            assertEquals(cepDomainMock.getEstado(), cepDomain.getEstado());
        });
    }
}
