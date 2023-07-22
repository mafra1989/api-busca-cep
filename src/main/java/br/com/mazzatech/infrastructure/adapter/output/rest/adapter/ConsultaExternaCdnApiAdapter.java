package br.com.mazzatech.infrastructure.adapter.output.rest.adapter;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.mapper.CepOutputMapper;
import br.com.mazzatech.infrastructure.adapter.output.rest.service.CepFeignClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


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

	@Override
	public CepDomain consultaCep(Long code) {

		delay();

		String cep = formatarCEP(String.valueOf(code));
		ResponseEntity<CdnCepResponseEntity> cdnCepResponseEntity = null;

		try {
			cdnCepResponseEntity = cepFeignClientService.buscaCep(cep);
		} catch (FeignException ex) {
			// throw new BusinessException(CodigoMensagem.CEP_NAO_ENCONTRADO);
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
		return cep.substring(0,5) + "-" + cep.substring(5,8);
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