package br.com.mazzatech.infrastructure.adapter.input.rest.controller;


import br.com.mazzatech.domain.model.CepDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.mazzatech.domain.port.input.CepInPort;
import br.com.mazzatech.infrastructure.adapter.input.rest.dto.response.CepDtoResponse;
import br.com.mazzatech.infrastructure.adapter.input.rest.mapper.CepInputMapper;

@RestController
public class CepControllerImpl implements CepController {

    @Autowired
    private CepInPort cepInPort;

    @Autowired
    private CepInputMapper mapper;

    @Override
    public ResponseEntity<CepDtoResponse> consultarCEP(Long code) {
        CepDomain cepDomain = cepInPort.consultarCep(code);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDto(cepDomain));
    }
}
