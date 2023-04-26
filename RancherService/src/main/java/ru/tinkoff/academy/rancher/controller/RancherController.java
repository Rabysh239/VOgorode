package ru.tinkoff.academy.rancher.controller;

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

    @PostMapping
    public RancherDto create(@RequestBody RancherBodyDto rancherBodyDto) {
        return service.create(rancherBodyDto);
    }

    @GetMapping("/{id}")
    public RancherDto get(@PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public RancherDto update(@PathVariable String id, @RequestBody RancherBodyDto rancherBodyDto) {
        return service.update(id, rancherBodyDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
