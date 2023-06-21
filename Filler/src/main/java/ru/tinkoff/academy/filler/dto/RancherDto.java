package ru.tinkoff.academy.filler.dto;

import lombok.Data;
import ru.tinkoff.academy.filler.model.Field;

import java.util.List;

@Data
public class RancherDto {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private List<Field> fields;
}
