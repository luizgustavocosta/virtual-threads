package com.costa.luiz.bankservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class IntegrationService {

    private static final Logger log = LoggerFactory.getLogger(IntegrationService.class);

    public ResponseEntity<String> getRiskTransaction(RestClient restClient) {
        log.info("Sync Risk txn {}", Thread.currentThread());
        return
                restClient
                        .get()
                        .uri("/{accountId}/{device}", "2", "50")
                        .retrieve().toEntity(String.class);
    }

    public ResponseEntity<Void> makeTransfer(RestClient restClient) {
        log.info("Sync Make transfer {}", Thread.currentThread());
        return restClient.post()
                .uri("/{sourceAccount}/{targetAccount}/{amount}",
                        "2",
                        "3",
                        "4")
                .retrieve().toBodilessEntity();
    }
}
