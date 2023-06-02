package ru.tinkoff.academy.rancher.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.rancher.dto.FieldDto;
import ru.tinkoff.academy.rancher.dto.InnerFieldDto;
import ru.tinkoff.academy.rancher.model.Field;

@Component
@RequiredArgsConstructor
public class FieldMapper {
    private final ModelMapper modelMapper;

    public FieldDto mapToDto(InnerFieldDto innerFieldDto, Long rancherId) {
        FieldDto fieldDto = modelMapper.map(innerFieldDto, FieldDto.class);
        fieldDto.setRancherId(rancherId);
        return fieldDto;
    }

    public Field mapToEntity(Long id, FieldDto fieldDto) {
        Field field = modelMapper.map(fieldDto, Field.class);
        field.setId(id);
        return field;
    }
}
