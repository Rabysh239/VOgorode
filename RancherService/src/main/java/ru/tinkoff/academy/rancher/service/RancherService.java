package ru.tinkoff.academy.rancher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.rancher.dto.CreatingRancherDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UpdatingRancherDto;
import ru.tinkoff.academy.rancher.dto.UserDto;
import ru.tinkoff.academy.rancher.exception.EntityNotFoundException;
import ru.tinkoff.academy.rancher.mapper.RancherMapper;
import ru.tinkoff.academy.rancher.model.Field;
import ru.tinkoff.academy.rancher.model.Rancher;
import ru.tinkoff.academy.rancher.model.User;
import ru.tinkoff.academy.rancher.repository.RancherRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RancherService {
    private final RancherRepository repository;
    private final RancherMapper mapper;
    private final UserService userService;
    private final FieldService fieldService;

    @Transactional
    public RancherDto create(CreatingRancherDto creatingRancherDto) {
        UserDto userDto = mapper.mapToUserDto(creatingRancherDto);
        User user = userService.create(userDto);
        Rancher rancher = mapper.mapToEntity(creatingRancherDto, user.getId());
        Rancher savedRancher = repository.save(rancher);
        List<Field> fields = creatingRancherDto.getFields().stream().map(f -> fieldService.create(f, savedRancher.getId())).toList();
        savedRancher.setFields(fields);
        return mapper.mapToDto(savedRancher, user);
    }

    public RancherDto get(Long id) {
        Rancher rancher = getRancher(id);
        User user = userService.get(rancher.getUserId());
        return mapper.mapToDto(rancher, user);
    }

    public RancherDto update(Long id, UpdatingRancherDto updatingRancherDto) {
        Rancher rancher = getRancher(id);
        UserDto userDto = mapper.mapToUserDto(updatingRancherDto);
        User user = userService.update(rancher.getUserId(), userDto);
        Rancher updatedRancher = mapper.mapToEntity(updatingRancherDto, user.getId());
        repository.save(updatedRancher);
        return mapper.mapToDto(updatedRancher, user);
    }

    public void delete(Long id) {
        Rancher rancher = getRancher(id);
        userService.delete(rancher.getUserId());
        repository.deleteById(id);
    }

    private Rancher getRancher(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("rancher", id));
    }
}
