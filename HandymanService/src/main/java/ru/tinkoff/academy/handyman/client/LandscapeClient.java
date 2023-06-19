package ru.tinkoff.academy.handyman.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.handyman.dto.UserDto;
import ru.tinkoff.academy.handyman.model.User;

import java.util.UUID;

@FeignClient(value = "user", url = "${landscape.url}")
public interface LandscapeClient {
    @PostMapping("/user")
    User create(@RequestBody UserDto userDto);

    @GetMapping("/user/{id}")
    User get(@PathVariable UUID id);

    @PutMapping("/user/{id}")
    User update(@PathVariable UUID id, @RequestBody UserDto userDto);

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable UUID id);

}
