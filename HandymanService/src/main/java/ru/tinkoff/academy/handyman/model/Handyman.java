package ru.tinkoff.academy.handyman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "handymans")
public class Handyman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID userId;
    @JsonIgnoreProperties(value = "handyman")
    @OneToMany(mappedBy = "handyman")
    private List<Skill> skills;
    @JsonIgnoreProperties(value = "handyman")
    @OneToMany(mappedBy = "handyman")
    private List<Account> accounts;
    @Column(nullable = false)
    private byte[] photo;
}
