package ru.tinkoff.academy.handyman.dto;

import lombok.Data;

@Data
public class UserDto {
    private String type;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
}
