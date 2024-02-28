package com.costa.luiz.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BankApp {

    public static void main(String[] args) {
        SpringApplication.run(BankApp.class, args);
    }
}

