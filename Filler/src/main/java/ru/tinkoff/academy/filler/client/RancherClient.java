package ru.tinkoff.academy.filler.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.tinkoff.academy.filler.dto.CreatingRancherDto;
import ru.tinkoff.academy.filler.dto.RancherDto;

@FeignClient(value = "rancher", url = "${url.rancher}")
public interface RancherClient {
    @PostMapping("/rancher")
    RancherDto create(@RequestBody CreatingRancherDto creatingRancherDto);
}
