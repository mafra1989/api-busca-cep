package br.com.mazzatech.infrastructure.adapter.output.rest.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Setter
@Service
@Slf4j
public class CepRestTemplateService<T, V> {

    @Autowired
    private RestTemplate restTemplate;

    static final String LOG_URL_GET = "Chamada GET para a URL [{0}]";

    public V get(String url, ParameterizedTypeReference<V> returnType) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<T> request = new HttpEntity<>(null, headers);

        log.info(LOG_URL_GET.replace("{0}", url));
        ResponseEntity restResult = restTemplate.exchange(url, HttpMethod.GET, request, returnType);

        return (V) restResult.getBody();
    }

}