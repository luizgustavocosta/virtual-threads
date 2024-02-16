package com.costa.luiz.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TransferServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferServiceApplication.class, args);
    }

}

@RestController
@RequestMapping("/v1/transfers")
class TransferController {

    private static final Logger log = LoggerFactory.getLogger(TransferController.class);

    @PostMapping("/{sourceAccount}/{targetAccount}/{amount}")
    String transferMoney(@PathVariable("sourceAccount") String sourceAccount,
                         @PathVariable("targetAccount") String targetAccount,
                         @PathVariable("amount") String amount) throws InterruptedException {
        log.info("Starting the transfer from account {} to {} of BRL {}", sourceAccount, targetAccount, amount);
        TimeUnit.SECONDS.sleep(1);
        log.info("{}", Thread.currentThread());
        return "Money transferred";
    }

    @GetMapping("/{accountId}")
    List<String> transfersById(@PathVariable String accountId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("Searching for all transactions of Account {}", accountId);
        log.info("{}", Thread.currentThread());
        return List.of("001 002 42.00 BRL");
    }
}
