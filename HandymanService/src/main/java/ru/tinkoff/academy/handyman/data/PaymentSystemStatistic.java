package ru.tinkoff.academy.handyman.data;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentSystemStatistic {
    private PaymentSystem paymentSystem;
    private Date minCreated;
    private Date maxCreated;

    public PaymentSystemStatistic(PaymentSystem paymentSystem, Date minCreated, Date maxCreated) {
        this.paymentSystem = paymentSystem;
        this.minCreated = minCreated;
        this.maxCreated = maxCreated;
    }
}
