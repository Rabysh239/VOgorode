package ru.tinkoff.academy.rancher.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String type;
    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
}
