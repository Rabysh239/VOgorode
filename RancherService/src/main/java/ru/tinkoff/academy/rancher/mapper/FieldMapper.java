package ru.tinkoff.academy.rancher.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.rancher.dto.InnerFieldDto;
import ru.tinkoff.academy.rancher.model.Field;

@Component
@RequiredArgsConstructor
public class FieldMapper {
    private final ModelMapper modelMapper;

    public Field mapToEntity(InnerFieldDto fieldDto) {
        return modelMapper.map(fieldDto, Field.class);
    }
}
