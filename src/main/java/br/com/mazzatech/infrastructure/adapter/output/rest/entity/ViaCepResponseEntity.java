package br.com.mazzatech.infrastructure.adapter.output.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class ViaCepResponseEntity {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private Long ibge;
    private String gia;
    private Long ddd;
    private String siafi;

}
