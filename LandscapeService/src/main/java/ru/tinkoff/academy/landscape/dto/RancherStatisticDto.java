package ru.tinkoff.academy.landscape.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RancherStatisticDto {
    private UUID userId;
    private Double minArea;
    private Double maxArea;
    private Double averageArea;
}
