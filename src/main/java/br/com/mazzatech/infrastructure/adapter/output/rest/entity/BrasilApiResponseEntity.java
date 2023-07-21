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
public class BrasilApiResponseEntity {

    private String cep;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String service;

}
