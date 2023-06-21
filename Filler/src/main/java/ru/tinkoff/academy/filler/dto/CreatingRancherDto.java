package ru.tinkoff.academy.filler.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatingRancherDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private List<InnerFieldDto> fields;
}
