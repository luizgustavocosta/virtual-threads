package com.costa.luiz.payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class PaymentItemProcessor implements ItemProcessor<Payment, Payment> {

    private static final Logger log = LoggerFactory.getLogger(PaymentItemProcessor.class);

    @Value("${payment.fee:2.2}")
    private float fee;

    @Override
    public Payment process(final Payment payment) {
        var account = payment.account();
        var amount = payment.amount();

        var transformedPayment = new Payment(account, amount.multiply(BigDecimal.valueOf(fee)), "OK");

        log.debug("Converting ({}) into ({})", payment, transformedPayment);

        return transformedPayment;
    }

}
