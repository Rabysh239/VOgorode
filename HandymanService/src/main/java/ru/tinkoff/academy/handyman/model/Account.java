package ru.tinkoff.academy.handyman.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import ru.tinkoff.academy.handyman.data.PaymentSystem;

import java.util.Date;

@Data
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    @DocumentReference
    private Handyman handyman;
    private String cardNumber;
    private PaymentSystem paymentSystem;
    private Date created = new Date();
}
