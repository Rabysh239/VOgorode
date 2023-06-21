package ru.tinkoff.academy.landscape.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tinkoff.academy.landscape.data.PageDto;
import ru.tinkoff.academy.landscape.dto.RancherStatisticDto;

@FeignClient(value = "rancher", url = "${url.rancher}")
public interface RancherClient {
    @GetMapping("/rancher/page/statistic")
    PageDto<RancherStatisticDto> getStatistics(@RequestParam int page, @RequestParam int size);
}
