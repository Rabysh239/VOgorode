package ru.tinkoff.academy.rancher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties(value = "fields")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Rancher rancher;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @Column(nullable = false)
    private Geometry area;
}
