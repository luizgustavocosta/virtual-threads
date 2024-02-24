package com.costa.luiz.bank.model.transfer;

import jakarta.validation.constraints.NotBlank;

public record Transfer(
        @NotBlank String amount, @NotBlank String from, @NotBlank String to, @NotBlank String device) {
}
