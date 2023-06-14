package ru.tinkoff.academy.handyman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.tinkoff.academy.handyman.data.PaymentSystem;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties(value = "accounts")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Handyman handyman;
    @Column(nullable = false)
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentSystem paymentSystem;
}
