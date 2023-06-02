package ru.tinkoff.academy.landscape.dto;

import lombok.Data;
import ru.tinkoff.academy.landscape.data.WorkType;

import java.util.UUID;

@Data
public class OrderDto {
    private Long fieldId;
    private UUID userId;
    private WorkType workType;
}
