package ru.tinkoff.academy.filler.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InnerFieldDto {
    private String address;
    private Double latitude;
    private Double longitude;
    private Double area;
}
