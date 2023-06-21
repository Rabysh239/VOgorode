package ru.tinkoff.academy.filler.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tinkoff.academy.filler.data.HandymanPaymentSystemStatistic;
import ru.tinkoff.academy.filler.data.RancherStatistic;
import ru.tinkoff.academy.filler.dto.PageDto;

import java.util.List;


@FeignClient(value = "landscape", url = "${url.landscape}")
public interface LandscapeClient {
    @GetMapping("/statistic/handyman/payment-system")
    List<HandymanPaymentSystemStatistic> getHandymanPaymentSystemStatistics();

    @GetMapping("/statistic/page/rancher")
    PageDto<RancherStatistic> getRancherStatistics(@RequestParam int page, @RequestParam int size);
}
