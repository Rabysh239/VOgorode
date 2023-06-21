package ru.tinkoff.academy.landscape.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.academy.landscape.data.HandymanPaymentSystemStatistic;
import ru.tinkoff.academy.landscape.data.RancherStatistic;
import ru.tinkoff.academy.landscape.service.StatisticService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("statistic")
public class StatisticController {
    private final StatisticService service;

    @Timed
    @GetMapping("/handyman/payment-system")
    public List<HandymanPaymentSystemStatistic> getRancherStatistics() {
        return service.getHandymanPaymentSystemStatistics();
    }

    @Timed
    @GetMapping("/page/rancher")
    public Page<RancherStatistic> getRancherStatistics(
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return service.getRancherStatistics(page, size);
    }
}
