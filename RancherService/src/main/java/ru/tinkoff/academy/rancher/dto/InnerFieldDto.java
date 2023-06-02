package ru.tinkoff.academy.rancher.dto;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
public class InnerFieldDto {
    private String address;
    private Double latitude;
    private Double longitude;
    private Geometry area;
}
