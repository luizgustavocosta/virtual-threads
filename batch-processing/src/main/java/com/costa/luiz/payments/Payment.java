package com.costa.luiz.payments;

import java.math.BigDecimal;

public record Payment(String account, BigDecimal amount, String status) {
}
