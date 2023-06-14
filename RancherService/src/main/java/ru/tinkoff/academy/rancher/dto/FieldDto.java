package ru.tinkoff.academy.rancher.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FieldDto extends InnerFieldDto {
    private Long rancherId;
}
