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

    @Value("${payment.threshold:10}")
    private float paymentThreshold;

    @Override
    public Payment process(final Payment payment) {
        var account = payment.account();
        var amount = payment.amount();

        BigDecimal calculatedFee = amount.multiply(BigDecimal.valueOf(fee));
        var status = "OK";
        if (calculatedFee.intValue() < paymentThreshold) {
            status = "SKIP";
        }
        var transformedPayment = new Payment(account, calculatedFee, status);
        log.debug("Converting ({}) into ({})", payment, transformedPayment);
        return transformedPayment;
    }

}
