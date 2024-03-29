package ru.tinkoff.academy.rancher.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.rancher.dto.UserBodyDto;
import ru.tinkoff.academy.rancher.model.User;

import java.util.UUID;

@FeignClient(value = "user", url = "${landscape.url}")
public interface LandscapeClient {
    @PostMapping("/user")
    User create(@RequestBody UserBodyDto userBodyDto);

    @GetMapping("/user/{id}")
    User get(@PathVariable UUID id);

    @PutMapping("/user/{id}")
    User update(@PathVariable UUID id, @RequestBody UserBodyDto userBodyDto);

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable UUID id);

}
