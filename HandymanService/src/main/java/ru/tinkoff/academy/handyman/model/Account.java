package ru.tinkoff.academy.handyman.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.tinkoff.academy.handyman.data.PaymentSystem;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Handyman handyman;
    @Column(nullable = false)
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentSystem paymentSystem;
    @CreationTimestamp
    private Timestamp created;
}
