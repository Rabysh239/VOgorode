package ru.tinkoff.academy.landscape.data;

import lombok.Data;

import java.util.Date;

@Data
public class HandymanPaymentSystemStatistic {
    private PaymentSystem paymentSystem;
    private Date minCreated;
    private Date maxCreated;
}
