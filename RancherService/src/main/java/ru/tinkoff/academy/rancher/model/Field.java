package ru.tinkoff.academy.rancher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document(collection = "fields")
public class Field {
    @Id
    private String id;
    @JsonIgnoreProperties(value = "fields")
    @DocumentReference
    private Rancher rancher;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double area;
}
