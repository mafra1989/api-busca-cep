package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.config.ProfileConfiguration;
import br.com.mazzatech.domain.exception.CepFeignException;
import br.com.mazzatech.domain.exception.CepNaoEncontradoException;
import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.MensagensNegociosEnum;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepFeignClientService;
import br.com.mazzatech.infrastructure.adapter.output.rest.utils.TimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Primary
@Qualifier("cdnApiAdapter")
public class ConsultaExternaCdnApiAdapter implements ConsultaExternaOutPort {

	@Autowired
	private CepFeignClientService cepFeignClientService;

	@Autowired
	private CepOutputMapper mapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProfileConfiguration profile;

	static final String LOG_URL_GET = "Chamada GET para a URL [{0}]";

	@Override
	public CepDomain consultaCep(Long code) {

		TimeUtil.delay();

		String cep = formatarCEP(String.valueOf(code));
		ResponseEntity<CdnCepResponseEntity> cdnCepResponseEntity = null;

		try {
			log.info(LOG_URL_GET.replace("{0}", profile.getUriCdnCep()));
			cdnCepResponseEntity = cepFeignClientService.buscaCep(cep);
		} catch (FeignException.NotFound ex) {
			throw new CepNaoEncontradoException(MensagensNegociosEnum.CEP_NAO_ENCONTRADO.getMensagem());
		} catch (FeignException ex) {
			throw new CepFeignException(MensagensNegociosEnum.ERRO_INTERNO.getCodigo(),
					MensagensNegociosEnum.ERRO_INTERNO.getMensagem());
		}

		try {
			String enderecoCepJson = objectMapper.writeValueAsString(cdnCepResponseEntity.getBody());
			log.info("Endereço retornado: {}", enderecoCepJson);
		} catch (JsonProcessingException ex) {
			log.info("Não foi possível exibir o endereço retornado");
		}

		return mapper.cdnCeptoDomain(cdnCepResponseEntity.getBody());
	}

	public static String formatarCEP(String cep){
		String cepCompleto = StringUtils.leftPad(cep, 8, "0");
		return cepCompleto.substring(0,5) + "-" + cepCompleto.substring(5,8);
	}

}