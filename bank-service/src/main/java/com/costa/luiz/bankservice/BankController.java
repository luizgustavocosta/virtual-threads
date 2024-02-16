package com.costa.luiz.bankservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/v1/api/bank")
public class BankController {

    private static final Logger log = LoggerFactory.getLogger(BankController.class);
    private final RestClient riskClient;
    private final RestClient transferClient;
    private final IntegrationServiceAsync integrationServiceAsync;
    private final IntegrationService integrationService;

    public BankController(RestClient.Builder restClientBuilder,
                          BankProperties bankProperties,
                          IntegrationServiceAsync integrationServiceAsync,
                          IntegrationService integrationService) {
        riskClient = restClientBuilder.baseUrl(bankProperties.getRiskServiceUrl()).build();
        transferClient = restClientBuilder.baseUrl(bankProperties.getTransferServiceUrl()).build();
        this.integrationServiceAsync = integrationServiceAsync;
        this.integrationService = integrationService;
    }

    @GetMapping("/block/{seconds}")
    public String delay(@PathVariable int seconds) {
        ResponseEntity<Void> result = riskClient.get()
                .uri("/delay/" + seconds)
                .retrieve()
                .toBodilessEntity();

        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());

        return Thread.currentThread().toString();
    }

    @GetMapping("/old-fashion")
    String oldFashion() throws ExecutionException, InterruptedException {
//        long start = System.nanoTime();
//        HttpClient client = HttpClient.newHttpClient();
////        HttpRequest request = HttpRequest.newBuilder()
////                .uri(URI.create("http://localhost:8081/lazy"))
////                .build();
//        CompletableFuture<HttpResponse<String>> responseCompletableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//        CompletableFuture<String> future1 = responseCompletableFuture.thenApply(x -> {
//            System.out.println(Thread.currentThread());
//            try {
//                return responseCompletableFuture.get().body() + " BODY";
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        long end = System.nanoTime();
//        log.info("{} {}", "Elapsed time in seconds - ", ((end - start) / 1.0E9));
//
//        return future1.get();


//        CompletableFuture<ResponseEntity<String>> future = CompletableFuture
//                .supplyAsync(() -> restClientForLazyService.get().retrieve().toEntity(String.class))
//                .thenApply(lazyReturn -> ResponseEntity.ok(lazyReturn.getBody() + " " +
//                        restClientForSlowService.get().retrieve().toEntity(String.class).getBody()))
//                .exceptionally(exception -> {
//                    log.error(exception.getMessage());
//                    return ResponseEntity
//                            .internalServerError()
//                            .body("We got the following error " + exception.getMessage());
//                });
//        long end = System.nanoTime();
//        log.info("{} {}", "Elapsed time in seconds - ", ((end - start) / 1.0E9));
//        return future.get().getBody();
        return "old fashion";
    }

    @GetMapping("/tech-talk")
    String newFashion() throws ExecutionException, InterruptedException {
        long start = System.nanoTime();
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<ResponseEntity<String>> risk = executorService.submit(() -> integrationService.getRiskTransaction(riskClient));
            Future<ResponseEntity<Void>> transfer = executorService.submit(() -> integrationService.makeTransfer(transferClient));
            log.info("{} {}", risk.get().getBody(), transfer.get().getStatusCode());
            log.info("{}", Thread.currentThread());
        }
        long end = System.nanoTime();
        log.info("{} {}", "Elapsed time in seconds - ", ((end - start) / 1.0E9));
        return Thread.currentThread().toString();
    }

    @GetMapping("/tech-talk-parallel")
    public String techTalkInParallel() throws ExecutionException, InterruptedException {
        log.info("TechTalk Parallel {}", Thread.currentThread());
        long start = System.nanoTime();
        CompletableFuture<ResponseEntity<String>> riskTransaction = integrationServiceAsync.getRiskTransaction(riskClient);
        CompletableFuture<ResponseEntity<Void>> responseEntityCompletableFuture = integrationServiceAsync.makeTransfer(transferClient);

        CompletableFuture.allOf(riskTransaction, responseEntityCompletableFuture).join();

        log.info("{} on {}", riskTransaction.get().getStatusCode(), Thread.currentThread());
        long end = System.nanoTime();
        log.info("{} {}", "Elapsed time in seconds - ", ((end - start) / 1.0E9));
        return Thread.currentThread().toString();
    }

    String randomSourceAccount() {
        return "AA";
    }

    String randomTargetAccount() {
        return "BB";
    }

    String randomAmount() {
        return "42.99";
    }

}
