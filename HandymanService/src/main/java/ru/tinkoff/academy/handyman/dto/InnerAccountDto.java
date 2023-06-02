package ru.tinkoff.academy.handyman.dto;

import lombok.Data;
import ru.tinkoff.academy.handyman.data.PaymentSystem;

@Data
public class InnerAccountDto {
    private String cardNumber;
    private PaymentSystem paymentSystem;
}
