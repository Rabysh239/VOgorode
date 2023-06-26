package ru.tinkoff.academy.rancher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "ranchers")
public class Rancher {
    @Id
    private String id;
    private UUID userId;
    @JsonIgnoreProperties(value = "rancher")
    @DocumentReference
    private List<Field> fields;
}
