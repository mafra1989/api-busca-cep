package br.com.mazzatech.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CepDomain {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private Long ibgeCidade;
    private Long ibgeEstado;
    private String origem;

}
