package ru.tinkoff.academy.rancher.dto;

import lombok.Data;

@Data
public class UserBodyDto {
    private String type;
    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
}
