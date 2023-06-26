package ru.tinkoff.academy.rancher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.rancher.dto.FieldDto;
import ru.tinkoff.academy.rancher.model.Field;
import ru.tinkoff.academy.rancher.service.FieldService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {
    private final FieldService service;

    @PostMapping
    public Field create(@RequestBody FieldDto fieldDto) {
        return service.create(fieldDto);
    }

    @GetMapping("/{id}")
    public Field get(@PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Field update(@PathVariable String id, @RequestBody FieldDto fieldDto) {
        return service.update(id, fieldDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
