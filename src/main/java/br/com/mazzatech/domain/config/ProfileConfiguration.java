package br.com.mazzatech.domain.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:profile.${ambiente}.properties")
public class ProfileConfiguration {

	@Value("${uriViaCep}")
	private String uriViaCep;

	@Value("${uriCdnCep}")
	private String uriCdnCep;

	@Value("${uriBrasilApi}")
	private String uriBrasilApi;

}
