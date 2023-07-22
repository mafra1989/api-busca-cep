package br.com.mazzatech.domain.strategy;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.port.output.ConsultaExternaOutPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
public class CepStrategy {

    private static final List<ConsultaExternaOutPort> processors = new ArrayList<ConsultaExternaOutPort>();

    @Autowired
    @Qualifier("brasilApiAdapter")
    private final ConsultaExternaOutPort brasilApiConsultaExternaOutPort;

    @Autowired
    @Qualifier("cdnApiAdapter")
    private final ConsultaExternaOutPort cdnApiConsultaExternaOutPort;

    @Autowired
    @Qualifier("viaCepAdapter")
    private final ConsultaExternaOutPort viaCepConsultaExternaOutPort;

    private void initProcessors() {
        processors.add(brasilApiConsultaExternaOutPort);
        processors.add(cdnApiConsultaExternaOutPort);
        processors.add(viaCepConsultaExternaOutPort);
    }

    public CepDomain consultaCep(Long code) throws ExecutionException, InterruptedException {

        if(processors.isEmpty()) {
            this.initProcessors();
        }

        final Executor executor = Executors.newFixedThreadPool(Math.min(processors.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        CompletableFuture[] completableFutures = processors.stream()
                .map(outPort -> CompletableFuture
                        .supplyAsync(() -> outPort.consultaCep(code), executor))
                .toArray(CompletableFuture[]::new);

        return (CepDomain) CompletableFuture.anyOf(completableFutures).join();
    }
}
