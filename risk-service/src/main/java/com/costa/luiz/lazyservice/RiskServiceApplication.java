package com.costa.luiz.lazyservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RiskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskServiceApplication.class, args);
    }

}

@RestController
@RequestMapping("/v1/risk/transaction")
class RiskTransactionController {

    private static final Logger log = LoggerFactory.getLogger(RiskTransactionController.class);

    @GetMapping("/{accountId}/{device}")
    String transactionCheck(@PathVariable String accountId,
                            @PathVariable String device) throws InterruptedException {
        log.info("Starting the transaction check for account {} using the device {}", accountId, device);
        TimeUnit.SECONDS.sleep(3);
        return "The risk is very low";
    }
}
