package ru.tinkoff.academy.handyman.dto;

import lombok.Data;

@Data
public class UpdatingHandymanDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private String photo;
}
