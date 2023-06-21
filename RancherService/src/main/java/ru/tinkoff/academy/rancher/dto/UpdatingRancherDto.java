package ru.tinkoff.academy.rancher.dto;

import lombok.Data;

@Data
public class UpdatingRancherDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
}