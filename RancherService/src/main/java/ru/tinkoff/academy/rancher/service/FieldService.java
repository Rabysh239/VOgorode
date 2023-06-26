package ru.tinkoff.academy.rancher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.rancher.dto.FieldDto;
import ru.tinkoff.academy.rancher.dto.InnerFieldDto;
import ru.tinkoff.academy.rancher.exception.EntityNotFoundException;
import ru.tinkoff.academy.rancher.mapper.FieldMapper;
import ru.tinkoff.academy.rancher.model.Field;
import ru.tinkoff.academy.rancher.model.Rancher;
import ru.tinkoff.academy.rancher.repository.FieldRepository;
import ru.tinkoff.academy.rancher.repository.RancherRepository;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository repository;
    private final FieldMapper mapper;
    private final RancherRepository rancherRepository;

    public Field create(InnerFieldDto innerFieldDto, String rancherId) {
        Field field = mapper.mapToEntity(innerFieldDto);
        Rancher rancher = getRancher(rancherId);
        field.setRancher(rancher);
        Field savedField = repository.save(field);
        rancher.getFields().add(savedField);
        rancherRepository.save(rancher);
        return savedField;
    }

    public Field create(FieldDto fieldDto) {
        Field field = mapper.mapToEntity(fieldDto);
        String rancherId = fieldDto.getRancherId();
        Rancher rancher = getRancher(rancherId);
        field.setRancher(rancher);
        return repository.save(field);
    }

    public Field get(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("field", id));
    }

    public Field update(String id, FieldDto fieldDto) {
        Field field = get(id);
        field.setAddress(fieldDto.getAddress());
        field.setLatitude(fieldDto.getLatitude());
        field.setLongitude(fieldDto.getLongitude());
        field.setArea(fieldDto.getArea());
        String rancherId = fieldDto.getRancherId();
        Rancher rancher = getRancher(rancherId);
        field.setRancher(rancher);
        return repository.save(field);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    private Rancher getRancher(String id) {
        return rancherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("rancher", id));
    }
}
