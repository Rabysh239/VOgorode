package ru.tinkoff.academy.rancher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ranchers")
public class Rancher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID userId;
    @JsonIgnoreProperties(value = "rancher")
    @OneToMany(mappedBy = "rancher")
    private List<Field> fields;
}
