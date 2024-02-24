package com.costa.luiz.bank.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BankProperties {

    @Value("${risk.service.url}")
    private String riskServiceUrl;


    @Value("${transfer.service.url}")
    private String transferServiceUrl;

    public String getRiskServiceUrl() {
        return riskServiceUrl;
    }

    public String getTransferServiceUrl() {
        return transferServiceUrl;
    }
}
