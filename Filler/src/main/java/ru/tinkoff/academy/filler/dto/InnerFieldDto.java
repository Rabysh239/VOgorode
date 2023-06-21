package ru.tinkoff.academy.filler.dto;

import lombok.Builder;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
@Builder
public class InnerFieldDto {
    private String address;
    private Double latitude;
    private Double longitude;
    private Geometry area;
}
