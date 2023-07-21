package br.com.mazzatech.infrastructure.adapter.input.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Pré-motor de prevenção de fraudes - Manutenção de dados",
                description = "APIs para manutenção de dados de prevenção de fraude e dados complementares na base do pré-motor"
        )
)
@Configuration
public class SwaggerConfig {
}
