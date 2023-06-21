package ru.tinkoff.academy.filler.dto;

import lombok.Builder;
import lombok.Data;
import ru.tinkoff.academy.filler.data.PaymentSystem;

@Data
@Builder
public class InnerAccountDto {
    private String cardNumber;
    private PaymentSystem paymentSystem;
}
