package br.com.mazzatech.infrastructure.adapter.output.rest.service;

import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${uriCdnCep}", name = "apicep")
public interface CepFeignClientService {

    @GetMapping("{code}.json")
    ResponseEntity<CdnCepResponseEntity> buscaCep(@PathVariable("code") String code);

}