package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.dto.UserBodyDto;
import ru.tinkoff.academy.landscape.dto.UserDto;
import ru.tinkoff.academy.landscape.exception.UserNotFoundException;
import ru.tinkoff.academy.landscape.mapper.UserMapper;
import ru.tinkoff.academy.landscape.model.User;
import ru.tinkoff.academy.landscape.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto create(UserBodyDto userBodyDto) {
        User user = mapper.mapToEntity(userBodyDto);
        User savedUser = repository.save(user);
        return mapper.mapToDto(savedUser);
    }

    public UserDto get(UUID id) {
        User user = getUser(id);
        return mapper.mapToDto(user);
    }

    @Transactional
    public UserDto update(UUID id, UserBodyDto userBodyDto) {
        User updatedUser = mapper.mapToEntity(userBodyDto);
        User user = getUser(id);
        updatedUser.setId(user.getId());
        updatedUser.setCreated(user.getCreated());
        User savedUser = repository.save(user);
        return mapper.mapToDto(savedUser);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private User getUser(UUID id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
