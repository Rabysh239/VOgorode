package ru.tinkoff.academy.rancher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.rancher.dto.CreatingRancherDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UpdatingRancherDto;
import ru.tinkoff.academy.rancher.dto.UserDto;
import ru.tinkoff.academy.rancher.exception.EntityNotFoundException;
import ru.tinkoff.academy.rancher.mapper.FieldMapper;
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
    private final FieldMapper fieldMapper;
    private final UserService userService;

    @Transactional
    public RancherDto create(CreatingRancherDto creatingRancherDto) {
        UserDto userDto = mapper.mapToUserDto(creatingRancherDto);
        User user = userService.create(userDto);
        Rancher rancher = mapper.mapToEntity(creatingRancherDto);
        rancher.setUserId(user.getId());
        List<Field> fields = creatingRancherDto.getFields().stream().map(fieldMapper::mapToEntity).toList();
        rancher.setFields(fields);
        Rancher savedRancher = repository.save(rancher);
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
        return mapper.mapToDto(rancher, user);
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
