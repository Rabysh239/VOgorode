package ru.tinkoff.academy.handyman.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.tinkoff.academy.handyman.model.Account;
import ru.tinkoff.academy.handyman.model.Skill;

import java.util.List;

@Data
public class HandymanDto {
    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    @JsonIgnoreProperties(value = {"handyman", "id"})
    private List<Skill> skills;
    @JsonIgnoreProperties(value = "handyman")
    private List<Account> accounts;
    private String photo;
}
