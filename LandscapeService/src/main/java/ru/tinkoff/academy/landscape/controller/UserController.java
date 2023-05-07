package ru.tinkoff.academy.landscape.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.landscape.dto.UserBodyDto;
import ru.tinkoff.academy.landscape.dto.UserDto;
import ru.tinkoff.academy.landscape.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @Timed
    @PostMapping
    public UserDto create(@RequestBody UserBodyDto userBodyDto) {
        return service.create(userBodyDto);
    }

    @Timed
    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @Timed
    @PutMapping("{id}")
    public UserDto update(@PathVariable UUID id, @RequestBody UserBodyDto userBodyDto) {
        return service.update(id, userBodyDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
