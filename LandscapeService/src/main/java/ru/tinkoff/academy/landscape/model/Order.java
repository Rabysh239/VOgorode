package ru.tinkoff.academy.landscape.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.tinkoff.academy.landscape.data.OrderStatus;
import ru.tinkoff.academy.landscape.data.WorkType;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long fieldId;
    @OneToOne
    @JoinColumn(nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkType workType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    @CreationTimestamp
    private Timestamp created;
}
