package ru.tinkoff.academy.filler.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.tinkoff.academy.filler.dto.CreatingHandymanDto;
import ru.tinkoff.academy.filler.dto.HandymanDto;

@FeignClient(value = "handyman", url = "${url.handyman}")
public interface HandymanClient {
    @PostMapping("/handyman")
    HandymanDto create(@RequestBody CreatingHandymanDto creatingHandymanDto);
}
