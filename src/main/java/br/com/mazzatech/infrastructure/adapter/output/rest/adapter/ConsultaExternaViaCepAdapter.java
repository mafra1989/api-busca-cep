package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.config.ProfileConfiguration;
import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.ViaCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepRestTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
@Qualifier("viaCepAdapter")
public class ConsultaExternaViaCepAdapter implements ConsultaExternaOutPort {

	@Autowired
	private CepRestTemplateService<Object, ViaCepResponseEntity> cepRestTemplateService;

	@Autowired
	private ProfileConfiguration profile;

	@Autowired
	private CepOutputMapper mapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public CepDomain consultaCep(Long code) {

		delay();

		String url = profile.getUriViaCep().concat(code.toString()).concat("/json/");
		ParameterizedTypeReference<ViaCepResponseEntity> typeRef = new ParameterizedTypeReference<ViaCepResponseEntity>() {};

		ViaCepResponseEntity viaCepResponseEntity = null;
		try {
			viaCepResponseEntity = cepRestTemplateService.get(url, typeRef);
		} catch (HttpClientErrorException ex) {
			// throw new BusinessException(CodigoMensagem.CEP_NAO_ENCONTRADO);
		}

		try {
			String enderecoCepJson = objectMapper.writeValueAsString(viaCepResponseEntity);
			log.info("Endereço retornado: {}", enderecoCepJson);
		} catch (JsonProcessingException ex) {
			log.info("Não foi possível exibir o endereço retornado");
		}

		return mapper.viaCeptoDomain(viaCepResponseEntity);
	}

	private static void delay() {
		try {
			int delay = ThreadLocalRandom.current().nextInt(500, 2000);
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}