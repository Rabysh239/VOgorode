package ru.tinkoff.academy.rancher.service;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.rancher.client.LandscapeClient;
import ru.tinkoff.academy.rancher.dto.UserDto;
import ru.tinkoff.academy.rancher.exception.InnerServiceUnavailableException;
import ru.tinkoff.academy.rancher.model.User;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final LandscapeClient landscapeClient;

    public User create(UserDto userDto) {
        try {
            return landscapeClient.create(userDto);
        } catch (RetryableException e) {
            throw new InnerServiceUnavailableException();
        }
    }

    public User get(UUID id) {
        try {
            return landscapeClient.get(id);
        } catch (RetryableException e) {
            throw new InnerServiceUnavailableException();
        }
    }

    public User update(UUID id, UserDto userDto) {
        try {
            return landscapeClient.update(id, userDto);
        } catch (RetryableException e) {
            throw new InnerServiceUnavailableException();
        }
    }

    public void delete(UUID id) {
        try {
            landscapeClient.delete(id);
        } catch (RetryableException e) {
            throw new InnerServiceUnavailableException();
        }
    }
}
