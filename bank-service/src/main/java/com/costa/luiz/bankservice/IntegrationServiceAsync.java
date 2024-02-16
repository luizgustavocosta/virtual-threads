package com.costa.luiz.bankservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CompletableFuture;

@Service
public class IntegrationServiceAsync {

    private static final Logger log = LoggerFactory.getLogger(IntegrationServiceAsync.class);

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity<String>> getRiskTransaction(RestClient restClient) {
        log.info("Async Risk txn {}", Thread.currentThread());
        return
                CompletableFuture.completedFuture(restClient
                        .get()
                        .uri("/{accountId}/{device}", "2", "50")
                        .retrieve().toEntity(String.class));
    }

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity<Void>> makeTransfer(RestClient restClient) {
        log.info("Async Make transfer {}", Thread.currentThread());
        return CompletableFuture.completedFuture(restClient.post()
                .uri("/{sourceAccount}/{targetAccount}/{amount}",
                        "2",
                        "3",
                        "4")
                .retrieve().toBodilessEntity());
    }
}
