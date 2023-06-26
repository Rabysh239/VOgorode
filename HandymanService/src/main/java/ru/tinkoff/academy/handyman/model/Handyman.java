package ru.tinkoff.academy.handyman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "handymans")
public class Handyman {
    @Id
    private String id;
    private UUID userId;
    @JsonIgnoreProperties(value = "handyman")
    @DocumentReference
    private List<Skill> skills;
    @JsonIgnoreProperties(value = "handyman")
    @DocumentReference
    private List<Account> accounts;
    private byte[] photo;
}
