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
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @JsonIgnoreProperties(value = "handyman")
    @OneToMany(mappedBy = "handyman", cascade = CascadeType.REMOVE)
    private List<Skill> skills;
    @JsonIgnoreProperties(value = "handyman")
    @OneToMany(mappedBy = "handyman", cascade = CascadeType.REMOVE)
    private List<Account> accounts;
    @Column(nullable = false)
    private byte[] photo;
}
