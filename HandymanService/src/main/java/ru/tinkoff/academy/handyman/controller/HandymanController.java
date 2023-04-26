package ru.tinkoff.academy.handyman.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.handyman.dto.HandymanBodyDto;
import ru.tinkoff.academy.handyman.dto.HandymanDto;
import ru.tinkoff.academy.handyman.service.HandymanService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/handyman")
public class HandymanController {
    private final HandymanService service;

    @PostMapping
    public HandymanDto create(@RequestBody HandymanBodyDto handymanBodyDto) {
        return service.create(handymanBodyDto);
    }

    @GetMapping("/{id}")
    public HandymanDto get(@PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public HandymanDto update(@PathVariable String id, @RequestBody HandymanBodyDto handymanBodyDto) {
        return service.update(id, handymanBodyDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
