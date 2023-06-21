package ru.tinkoff.academy.filler.dto;

import lombok.Data;
import ru.tinkoff.academy.filler.model.Account;
import ru.tinkoff.academy.filler.model.Skill;

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
    private List<Skill> skills;
    private List<Account> accounts;
    private String photo;
}
