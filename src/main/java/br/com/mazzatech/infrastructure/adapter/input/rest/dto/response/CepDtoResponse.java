package br.com.mazzatech.infrastructure.adapter.input.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CepDtoResponse {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long ibgeCidade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long ibgeEstado;

}
