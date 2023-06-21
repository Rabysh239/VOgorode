package ru.tinkoff.academy.filler.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatingHandymanDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private String photo;
    private List<InnerSkillDto> skills;
    private List<InnerAccountDto> accounts;
}
