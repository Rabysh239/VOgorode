package ru.tinkoff.academy.rancher.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.rancher.data.Statistic;
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
    public RancherDto get(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping("/page/statistic")
    public Page<Statistic> getStatistics(@RequestParam int page, @RequestParam int size) {
        return service.getStatistics(page, size);
    }

    @Timed
    @PutMapping("/{id}")
    public RancherDto update(@PathVariable String id, @RequestBody UpdatingRancherDto updatingRancherDto) {
        return service.update(id, updatingRancherDto);
    }

    @Timed
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
