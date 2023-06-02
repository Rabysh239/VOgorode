package ru.tinkoff.academy.rancher.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.rancher.dto.CreatingRancherDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UpdatingRancherDto;
import ru.tinkoff.academy.rancher.service.RancherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rancher")
public class RancherController {
    private final RancherService service;

    @Timed
    @PostMapping
    public RancherDto create(@RequestBody CreatingRancherDto creatingRancherDto) {
        return service.create(creatingRancherDto);
    }

    @Timed
    @GetMapping("/{id}")
    public RancherDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @Timed
    @PutMapping("/{id}")
    public RancherDto update(@PathVariable Long id, @RequestBody UpdatingRancherDto updatingRancherDto) {
        return service.update(id, updatingRancherDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
