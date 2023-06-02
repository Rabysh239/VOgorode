package ru.tinkoff.academy.rancher.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreatingRancherDto extends UpdatingRancherDto {
    private List<InnerFieldDto> fields;
}
