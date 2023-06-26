package ru.tinkoff.academy.rancher.dto;

import lombok.Data;

@Data
public class InnerFieldDto {
    private String address;
    private Double latitude;
    private Double longitude;
    private Double area;
}
