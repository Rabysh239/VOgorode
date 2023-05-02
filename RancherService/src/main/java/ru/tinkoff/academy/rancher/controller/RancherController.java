package ru.tinkoff.academy.rancher.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.rancher.dto.RancherBodyDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.service.RancherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rancher")
public class RancherController {
    private final RancherService service;

    @Timed
    @PostMapping
    public RancherDto create(@RequestBody RancherBodyDto rancherBodyDto) {
        return service.create(rancherBodyDto);
    }

    @Timed
    @GetMapping("/{id}")
    public RancherDto get(@PathVariable String id) {
        return service.get(id);
    }

    @Timed
    @PutMapping("/{id}")
    public RancherDto update(@PathVariable String id, @RequestBody RancherBodyDto rancherBodyDto) {
        return service.update(id, rancherBodyDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
