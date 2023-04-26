package ru.tinkoff.academy.handyman.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Handyman {
    @Id
    private String id;
    private UUID userId;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
