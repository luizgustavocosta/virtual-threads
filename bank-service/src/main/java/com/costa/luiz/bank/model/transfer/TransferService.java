package com.costa.luiz.bank.model.transfer;

import com.costa.luiz.bank.configuration.BankProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TransferService {
    private static final Logger log = LoggerFactory.getLogger(TransferService.class);
    private static final String TRANSFER_URI = "/{sourceAccount}/{targetAccount}/{amount}";
    private static final String RISK_URI = "/transaction/{accountId}/{device}";

    private final RestClient riskClient;
    private final RestClient transferClient;

    public TransferService(RestClient.Builder restClientBuilder,
                           BankProperties bankProperties) {
        riskClient = restClientBuilder.baseUrl(bankProperties.getRiskServiceUrl()).build();
        transferClient = restClientBuilder.baseUrl(bankProperties.getTransferServiceUrl()).build();
    }

    public String transfer(Transfer transfer) {
        try {
            var risk = getRiskTransaction(transfer).getBody();
            switch (risk) {
                case "LOW", "VERY_LOW" -> makeTransfer(transfer);
                case "HIGH" -> requestAdditionalValidation();
                case null -> throw new IllegalArgumentException("Risk cannot be identified, try in few minutes.");
                default -> throw new IllegalStateException("Risk cannot be identified");
            }
            return String.format("Amount of %s transferred to account %s", transfer.amount(), transfer.to());
        } catch (RestClientException restClientException) {
            throw new IllegalStateException("Transaction cannot be completed due to " + restClientException.getMessage());
        }
    }

    private void requestAdditionalValidation() {
        log.info("Fake method to call an additional validation");
    }

    private ResponseEntity<String> getRiskTransaction(Transfer transfer) {
        log.info("Sync Risk txn {}", Thread.currentThread());
        return
                riskClient
                        .get()
                        .uri(RISK_URI, transfer.from(), transfer.device())
                        .retrieve()
                        .toEntity(String.class);
    }

    private ResponseEntity<Void> makeTransfer(Transfer transfer) {
        log.info("Sync Make transfer {}", Thread.currentThread());
        return transferClient.post()
                .uri(TRANSFER_URI,
                        transfer.from(), transfer.to(), transfer.amount())
                .retrieve().toBodilessEntity();
    }

    public String newTransferOldFashion(Transfer transfer) {
        try {
            CompletableFuture<ResponseEntity<String>> bankFlow = CompletableFuture
                    .supplyAsync(() -> riskClient.get()
                            .uri(RISK_URI, transfer.from(), transfer.device())
                            .retrieve().toEntity(String.class))
                    .thenApply(lazyReturn ->
                            ResponseEntity.ok(lazyReturn.getBody() + " " +
                                    transferClient
                                            .post()
                                            .uri(TRANSFER_URI,
                                                    transfer.from(),
                                                    transfer.to(),
                                                    transfer.amount())
                                            .retrieve()
                                            .toEntity(String.class).getBody()))
                    .exceptionally(exception -> {
                        log.error(exception.getMessage());
                        return ResponseEntity
                                .internalServerError()
                                .body("We got the following error " + exception.getMessage());
                    });
            CompletableFuture.allOf(bankFlow).join();
            log.info("BankFlow {}", bankFlow.get());
            return bankFlow.get().getBody();
        } catch (ExecutionException | InterruptedException exception) {
            Thread.currentThread().interrupt();
            return "The following error happened " + exception.getCause();
        }
    }

    @Async("customAsyncExecutor")
    public CompletableFuture<ResponseEntity<String>> getRiskTransactionAsync(Transfer newTransfer) {
        log.info("Async started the risk call using {}", Thread.currentThread());
        return
                CompletableFuture.completedFuture(riskClient
                        .get()
                        .uri(RISK_URI, newTransfer.from(), newTransfer.device())
                        .retrieve().toEntity(String.class));
    }

    @Async("customAsyncExecutor")
    public CompletableFuture<ResponseEntity<Void>> makeTransferAsync(Transfer newTransfer) {
        log.info("Async started the transfer call using {}", Thread.currentThread());
        return CompletableFuture.completedFuture(transferClient
                .post()
                .uri(TRANSFER_URI,
                        newTransfer.from(),
                        newTransfer.to(),
                        newTransfer.amount())
                .retrieve().toBodilessEntity());
    }

    public String newTransferConcurrent(Transfer newTransfer) {
        log.info("Current thread {}", Thread.currentThread());
        // try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) { to use Virtual Threads
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Future<ResponseEntity<String>> risk = executorService.submit(() -> getRiskTransaction(newTransfer));
            Future<ResponseEntity<Void>> transfer = executorService.submit(() -> makeTransfer(newTransfer));
            if (risk.isDone() && transfer.isDone()) {
                log.info("{} {}", risk.get().getBody(), transfer.get().getStatusCode());
            }
            return "Money transferred";
        } catch (ExecutionException | InterruptedException exception) {
            Thread.currentThread().interrupt();
            return """
                    The following error $error happened
                    Please, try again.
                    """.replace("$error", exception.getMessage());
        }
    }
}
