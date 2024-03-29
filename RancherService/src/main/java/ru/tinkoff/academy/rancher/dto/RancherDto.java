package ru.tinkoff.academy.rancher.dto;

import lombok.Data;

import java.util.List;

@Data
public class RancherDto {
    private String id;
    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private Double area;
    private List<String> jobs;
}
