package com.costa.luiz.batchprocessing;

import java.math.BigDecimal;

public record Payment(String account, BigDecimal amount, String status) {
}
