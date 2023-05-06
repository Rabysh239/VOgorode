package ru.tinkoff.academy.rancher.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Rancher {
    @Id
    private String id;
    private UUID userId;
    private Double latitude;
    private Double longitude;
    private Double area;
    private List<String> jobs;
}
