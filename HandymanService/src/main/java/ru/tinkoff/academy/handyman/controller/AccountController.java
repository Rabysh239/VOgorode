package ru.tinkoff.academy.handyman.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.academy.handyman.data.PaymentSystemStatistic;
import ru.tinkoff.academy.handyman.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @GetMapping("/statistic/payment-system")
    public List<PaymentSystemStatistic> getStatistics() {
        return service.getPaymentSystemStatistics();
    }
}
