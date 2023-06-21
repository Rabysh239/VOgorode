package ru.tinkoff.academy.filler.model;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Data
public class Field {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Geometry area;
}
