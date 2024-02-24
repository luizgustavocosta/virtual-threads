package com.costa.luiz.bank.model.transfer;

import com.costa.luiz.bank.configuration.MDCInterceptor;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/transfers")
public class TransferController {

    private static final Logger log = LoggerFactory.getLogger(TransferController.class);
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    ResponseEntity<String> createANewTransfer(@RequestBody @Valid Transfer newTransfer) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transferService.newTransfer(newTransfer));
    }

    @GetMapping("/old-fashion")
    ResponseEntity<String> oldFashion() {
        var correlationId = MDC.get(MDCInterceptor.CORRELATION_ID_KEY);
        log.info("{} - Starting the old-fashion processing - {}", correlationId, Thread.currentThread());
        Instant start = Instant.now();
        var oldFashion = transferService.newTransferOldFashion(mockTransfer());
        log.info("{}  - End of execution. Took {} milliseconds, using {}",
                correlationId,
                Duration.between(start, Instant.now()).toMillis(),
                Thread.currentThread());
        return ResponseEntity
                .ok(oldFashion);
    }

    private Transfer mockTransfer() {
        return new Transfer("10", "11", "12", "Laptop");
    }


    @GetMapping("/concurrent")
    ResponseEntity<String> concurrent() {
        var correlationId = MDC.get(MDCInterceptor.CORRELATION_ID_KEY);
        log.info("{} - Starting the parallel processing", correlationId);
        Instant start = Instant.now();
        String concurrent = transferService.newTransferConcurrent(mockTransfer());
        log.info("{}  - End of execution. Took {} milliseconds, using {}",
                correlationId,
                Duration.between(start, Instant.now()).toMillis(),
                Thread.currentThread());
        return ResponseEntity.ok(concurrent);
    }

    @GetMapping("/parallel")
    public ResponseEntity<String> parallel() throws ExecutionException, InterruptedException {
        Transfer transfer = mockTransfer();
        var correlationId = MDC.get("CorrelationId");
        log.info("Starting the parallel processing of {}", correlationId);
        long start = System.nanoTime();
        CompletableFuture<ResponseEntity<String>> riskAsync = transferService.getRiskTransactionAsync(transfer);
        CompletableFuture<ResponseEntity<Void>> transferAsync = transferService.makeTransferAsync(transfer);
        CompletableFuture.allOf(riskAsync, transferAsync).join();
        long end = System.nanoTime();
        log.info("End of execution of {} took {} seconds, using {}",
                correlationId,
                ((end - start) / 1.0E9),
                Thread.currentThread());
        return ResponseEntity.ok(riskAsync.get().getBody() + transferAsync.get());
    }
}
