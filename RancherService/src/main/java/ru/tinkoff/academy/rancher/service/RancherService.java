package ru.tinkoff.academy.rancher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.academy.rancher.data.Statistic;
import ru.tinkoff.academy.rancher.dto.CreatingRancherDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UpdatingRancherDto;
import ru.tinkoff.academy.rancher.dto.UserDto;
import ru.tinkoff.academy.rancher.exception.EntityNotFoundException;
import ru.tinkoff.academy.rancher.mapper.RancherMapper;
import ru.tinkoff.academy.rancher.model.Rancher;
import ru.tinkoff.academy.rancher.model.User;
import ru.tinkoff.academy.rancher.repository.RancherRepository;

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
        Rancher rancher = mapper.mapToEntity(creatingRancherDto);
        rancher.setUserId(user.getId());
        Rancher savedRancher = repository.save(rancher);
        creatingRancherDto.getFields().forEach(f -> fieldService.create(f, savedRancher.getId()));
        return mapper.mapToDto(savedRancher, user);
    }

    public RancherDto get(String id) {
        Rancher rancher = getRancher(id);
        User user = userService.get(rancher.getUserId());
        return mapper.mapToDto(rancher, user);
    }

    public Page<Statistic> getStatistics(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
        return repository.getStatistics(pageRequest);
    }

    public RancherDto update(String id, UpdatingRancherDto updatingRancherDto) {
        Rancher rancher = getRancher(id);
        UserDto userDto = mapper.mapToUserDto(updatingRancherDto);
        User user = userService.update(rancher.getUserId(), userDto);
        return mapper.mapToDto(rancher, user);
    }

    public void delete(String id) {
        Rancher rancher = getRancher(id);
        userService.delete(rancher.getUserId());
        repository.deleteById(id);
    }

    private Rancher getRancher(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("rancher", id));
    }
}
