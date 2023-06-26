package ru.tinkoff.academy.handyman.dto;

import lombok.Data;
import ru.tinkoff.academy.handyman.data.PaymentSystem;

@Data
public class AccountDto {
    private String handymanId;
    private String cardNumber;
    private PaymentSystem paymentSystem;
}
