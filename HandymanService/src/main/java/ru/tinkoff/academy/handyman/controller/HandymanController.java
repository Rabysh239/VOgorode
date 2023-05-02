package ru.tinkoff.academy.handyman.controller;

import io.micrometer.core.annotation.Timed;
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

    @Timed
    @PostMapping
    public HandymanDto create(@RequestBody HandymanBodyDto handymanBodyDto) {
        return service.create(handymanBodyDto);
    }

    @Timed
    @GetMapping("/{id}")
    public HandymanDto get(@PathVariable String id) {
        return service.get(id);
    }

    @Timed
    @PutMapping("/{id}")
    public HandymanDto update(@PathVariable String id, @RequestBody HandymanBodyDto handymanBodyDto) {
        return service.update(id, handymanBodyDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
