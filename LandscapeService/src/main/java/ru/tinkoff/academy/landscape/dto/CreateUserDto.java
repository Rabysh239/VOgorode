package ru.tinkoff.academy.landscape.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String type;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
}
