package ru.tinkoff.academy.rancher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.rancher.dto.RancherBodyDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UserBodyDto;
import ru.tinkoff.academy.rancher.exception.RancherNotFoundException;
import ru.tinkoff.academy.rancher.mapper.RancherMapper;
import ru.tinkoff.academy.rancher.model.Rancher;
import ru.tinkoff.academy.rancher.model.User;
import ru.tinkoff.academy.rancher.repository.RancherRepository;

@Service
@RequiredArgsConstructor
public class RancherService {
    private final RancherRepository repository;
    private final UserService userService;
    private final RancherMapper mapper;

    public RancherDto create(RancherBodyDto rancherBodyDto) {
        UserBodyDto userBodyDto = mapper.mapToUserBodies(rancherBodyDto);
        User user = userService.create(userBodyDto);
        Rancher rancher = mapper.mapToEntity(rancherBodyDto, user);
        repository.insert(rancher);
        return mapper.mapToDto(rancher, user);
    }

    public RancherDto get(String id) {
        Rancher rancher = getRancher(id);
        User user = userService.get(rancher.getUserId());
        return mapper.mapToDto(rancher, user);
    }

    public RancherDto update(String id, RancherBodyDto rancherBodyDto) {
        Rancher rancher = getRancher(id);
        UserBodyDto userBodyDto = mapper.mapToUserBodies(rancherBodyDto);
        User user = userService.update(rancher.getUserId(), userBodyDto);
        Rancher updatedRancher = mapper.mapToEntity(rancherBodyDto, user);
        updatedRancher.setId(id);
        repository.save(updatedRancher);
        return mapper.mapToDto(updatedRancher, user);
    }

    public void delete(String id) {
        Rancher rancher = getRancher(id);
        userService.delete(rancher.getUserId());
        repository.deleteById(id);
    }

    private Rancher getRancher(String id) {
        return repository.findById(id).orElseThrow(() -> new RancherNotFoundException(id));
    }
}
