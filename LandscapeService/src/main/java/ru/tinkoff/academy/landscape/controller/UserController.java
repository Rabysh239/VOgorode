package ru.tinkoff.academy.landscape.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.landscape.dto.CreateUserDto;
import ru.tinkoff.academy.landscape.model.User;
import ru.tinkoff.academy.landscape.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @Timed
    @PostMapping
    public User create(@RequestBody CreateUserDto createUserDto) {
        return service.create(createUserDto);
    }

    @Timed
    @GetMapping("/{id}")
    public User get(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @Timed
    @PutMapping("{id}")
    public User update(@PathVariable UUID id, @RequestBody CreateUserDto createUserDto) {
        return service.update(id, createUserDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
