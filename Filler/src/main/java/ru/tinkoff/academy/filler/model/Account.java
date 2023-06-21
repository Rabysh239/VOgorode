package ru.tinkoff.academy.filler.model;

import lombok.Data;
import ru.tinkoff.academy.filler.data.PaymentSystem;

@Data
public class Account {
    private Long id;
    private String cardNumber;
    private PaymentSystem paymentSystem;
}
