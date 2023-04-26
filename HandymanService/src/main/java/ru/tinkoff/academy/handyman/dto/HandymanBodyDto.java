package ru.tinkoff.academy.handyman.dto;

import lombok.Data;

import java.util.List;

@Data
public class HandymanBodyDto {
    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
