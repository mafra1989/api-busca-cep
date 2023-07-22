package br.com.mazzatech.domain.strategy;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@AllArgsConstructor
public class CepStrategy {

    @Autowired
    @Qualifier("brasilApiAdapter")
    private final ConsultaExternaOutPort brasilApiConsultaExternaOutPort;

    @Autowired
    @Qualifier("cdnApiAdapter")
    private final ConsultaExternaOutPort cdnApiConsultaExternaOutPort;

    @Autowired
    @Qualifier("viaCepAdapter")
    private final ConsultaExternaOutPort viaCepConsultaExternaOutPort;

    public CepDomain consultaCep(Long code) throws ExecutionException, InterruptedException {

        CompletableFuture<CepDomain> brasilApi = CompletableFuture.supplyAsync(() -> {
            return brasilApiConsultaExternaOutPort.consultaCep(code);
        });

        CompletableFuture<CepDomain> cdnCepApi = CompletableFuture.supplyAsync(() -> {
            return cdnApiConsultaExternaOutPort.consultaCep(code);
        });

        CompletableFuture<CepDomain> viaCep = CompletableFuture.supplyAsync(() -> {
            return viaCepConsultaExternaOutPort.consultaCep(code);
        });

        Object anyOfFuture = CompletableFuture.anyOf(brasilApi, cdnCepApi, viaCep).join();

        return (CepDomain) anyOfFuture;
    }
}
