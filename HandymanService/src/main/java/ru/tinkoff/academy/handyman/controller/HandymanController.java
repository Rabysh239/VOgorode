package ru.tinkoff.academy.handyman.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.handyman.dto.CreatingHandymanDto;
import ru.tinkoff.academy.handyman.dto.HandymanDto;
import ru.tinkoff.academy.handyman.service.HandymanService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/handyman")
public class HandymanController {
    private final HandymanService service;

    @Timed
    @PostMapping
    public HandymanDto create(@RequestBody CreatingHandymanDto creatingHandymanDto) {
        return service.create(creatingHandymanDto);
    }

    @Timed
    @GetMapping("/{id}")
    public HandymanDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @Timed
    @PutMapping("/{id}")
    public HandymanDto update(@PathVariable Long id, @RequestBody CreatingHandymanDto creatingHandymanDto) {
        return service.update(id, creatingHandymanDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
