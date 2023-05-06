package ru.tinkoff.academy.handyman.dto;

import lombok.Data;

import java.util.List;

@Data
public class HandymanDto {
    private String id;
    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
