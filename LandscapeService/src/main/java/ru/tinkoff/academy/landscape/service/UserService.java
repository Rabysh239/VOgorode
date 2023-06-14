package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.dto.CreateUserDto;
import ru.tinkoff.academy.landscape.exception.EntityNotFoundException;
import ru.tinkoff.academy.landscape.mapper.UserMapper;
import ru.tinkoff.academy.landscape.model.User;
import ru.tinkoff.academy.landscape.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public User create(CreateUserDto createUserDto) {
        User user = mapper.mapToEntity(createUserDto);
        return repository.save(user);
    }

    public User get(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("user", String.valueOf(id)));
    }

    public User update(UUID id, CreateUserDto createUserDto) {
        User user = get(id);
        User updatedUser = mapper.mapToEntity(createUserDto);
        user.setType(updatedUser.getType());
        user.setLogin(updatedUser.getLogin());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setLatitude(updatedUser.getLatitude());
        user.setLongitude(updatedUser.getLongitude());
        return repository.save(user);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
