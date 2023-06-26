package ru.tinkoff.academy.handyman.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document(collection = "skills")
public class Skill {
    @Id
    private String id;
    private String name;
    @DocumentReference
    private Handyman handyman;
}
