package ru.tinkoff.academy.landscape.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tinkoff.academy.landscape.data.HandymanPaymentSystemStatistic;

import java.util.List;

@FeignClient(value = "handyman", url = "${url.handyman}")
public interface HandymanClient {
    @GetMapping("/account/statistic/payment-system")
    List<HandymanPaymentSystemStatistic> getPaymentSystemStatistics();
}
