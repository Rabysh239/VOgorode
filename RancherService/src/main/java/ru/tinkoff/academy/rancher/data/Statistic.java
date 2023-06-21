package ru.tinkoff.academy.rancher.data;

import lombok.Data;

import java.util.UUID;

@Data
public class Statistic {
    private UUID userId;
    private Double minArea;
    private Double maxArea;
    private Double averageArea;

    public Statistic(UUID userId, Double minArea, Double maxArea, Double averageArea) {
        this.userId = userId;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.averageArea = averageArea;
    }
}
