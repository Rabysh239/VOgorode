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
    @Column(name = "field_id", nullable = false)
    private Long fieldId;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "work_type", nullable = false)
    private WorkType workType;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    @CreationTimestamp
    private Timestamp created;
}
