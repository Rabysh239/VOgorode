package ru.tinkoff.academy.rancher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.tinkoff.academy.rancher.model.Field;

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
    @JsonIgnoreProperties(value = "rancher")
    private List<Field> fields;
}
