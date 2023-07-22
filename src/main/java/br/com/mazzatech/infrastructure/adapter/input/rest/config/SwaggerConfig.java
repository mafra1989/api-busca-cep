package br.com.mazzatech.infrastructure.adapter.input.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Busca CEP",
                description = "API para buscar endereço com o Cep"
        )
)
@Configuration
public class SwaggerConfig {
}
