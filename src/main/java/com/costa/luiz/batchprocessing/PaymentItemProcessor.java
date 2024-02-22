package com.costa.luiz.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class PaymentItemProcessor implements ItemProcessor<Payment, Payment> {

    private static final Logger log = LoggerFactory.getLogger(PaymentItemProcessor.class);

    @Override
    public Payment process(final Payment payment) {
        var account = payment.account();
        var amount = payment.amount();

        var transformedPayment = new Payment(account, amount.multiply(new BigDecimal("2.2")), "OK");

        log.info("Converting ({}) into ({})", payment, transformedPayment);

        return transformedPayment;
    }

}
