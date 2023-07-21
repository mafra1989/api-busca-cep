package br.com.mazzatech.infrastructure.adapter.input.rest.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mazzatech.infrastructure.adapter.input.rest.dto.response.CepDtoResponse;


@RequestMapping("/v1/cep")
public interface CepController {

    @GetMapping(value = "/{code}/consultar", produces = "application/json")
    ResponseEntity<CepDtoResponse> consultarCEP(
            @Valid @PathVariable Long code);
}
