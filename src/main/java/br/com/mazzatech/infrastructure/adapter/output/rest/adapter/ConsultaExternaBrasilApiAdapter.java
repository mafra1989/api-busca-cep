package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.config.ProfileConfiguration;
import br.com.mazzatech.domain.exception.CepNaoEncontradoException;
import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.MensagensNegociosEnum;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.BrasilApiResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepRestTemplateService;
import br.com.mazzatech.infrastructure.adapter.output.rest.utils.TimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;


@Slf4j
@Component
@Qualifier("brasilApiAdapter")
public class ConsultaExternaBrasilApiAdapter implements ConsultaExternaOutPort {

	@Autowired
	private CepRestTemplateService<Object, BrasilApiResponseEntity> brasilApiRestTemplateService;

	@Autowired
	private ProfileConfiguration profile;

	@Autowired
	private CepOutputMapper mapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public CepDomain consultaCep(Long code) {

		TimeUtil.delay();

		String url = profile.getUriBrasilApi().concat(code.toString());
		ParameterizedTypeReference<BrasilApiResponseEntity> typeRef = new ParameterizedTypeReference<BrasilApiResponseEntity>() {};

		BrasilApiResponseEntity brasilApiResponseEntity;
		try {
			brasilApiResponseEntity = brasilApiRestTemplateService.get(url, typeRef);
		} catch (HttpClientErrorException ex) {
			throw new CepNaoEncontradoException(MensagensNegociosEnum.CEP_NAO_ENCONTRADO.getMensagem());
		}

		try {
			String enderecoCepJson = objectMapper.writeValueAsString(brasilApiResponseEntity);
			log.info("Endereço retornado: {}", enderecoCepJson);
		} catch (JsonProcessingException ex) {
			log.info("Não foi possível exibir o endereço retornado");
		}

		return mapper.brasilApitoDomain(brasilApiResponseEntity);
	}

}