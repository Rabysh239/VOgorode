package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.dto.HandymanBodyDto;
import ru.tinkoff.academy.handyman.dto.HandymanDto;
import ru.tinkoff.academy.handyman.dto.UserBodyDto;
import ru.tinkoff.academy.handyman.exception.HandymanNotFoundException;
import ru.tinkoff.academy.handyman.mapper.HandymanMapper;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.User;
import ru.tinkoff.academy.handyman.repository.HandymanRepository;

@Service
@RequiredArgsConstructor
public class HandymanService {
    private final HandymanRepository repository;
    private final UserService userService;
    private final HandymanMapper mapper;

    public HandymanDto create(HandymanBodyDto handymanBodyDto) {
        UserBodyDto userBodyDto = mapper.mapToUserBodies(handymanBodyDto);
        User user = userService.create(userBodyDto);
        Handyman handyman = mapper.mapToEntity(handymanBodyDto, user);
        repository.insert(handyman);
        return mapper.mapToDto(handyman, user);
    }

    public HandymanDto get(String id) {
        Handyman handyman = getHandyman(id);
        User user = userService.get(handyman.getUserId());
        return mapper.mapToDto(handyman, user);
    }

    public HandymanDto update(String id, HandymanBodyDto handymanBodyDto) {
        Handyman handyman = getHandyman(id);
        UserBodyDto userBodyDto = mapper.mapToUserBodies(handymanBodyDto);
        User user = userService.update(handyman.getUserId(), userBodyDto);
        Handyman updatedHandyman = mapper.mapToEntity(handymanBodyDto, user);
        updatedHandyman.setId(id);
        repository.save(updatedHandyman);
        return mapper.mapToDto(updatedHandyman, user);
    }

    public void delete(String id) {
        Handyman handyman = getHandyman(id);
        userService.delete(handyman.getUserId());
        repository.deleteById(id);
    }

    private Handyman getHandyman(String id) {
        return repository.findById(id).orElseThrow(() -> new HandymanNotFoundException(id));
    }
}
