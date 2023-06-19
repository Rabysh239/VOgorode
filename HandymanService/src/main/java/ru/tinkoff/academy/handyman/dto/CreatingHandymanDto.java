package ru.tinkoff.academy.handyman.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreatingHandymanDto extends UpdatingHandymanDto {
    private List<InnerSkillDto> skills;
    private List<InnerAccountDto> accounts;
}
