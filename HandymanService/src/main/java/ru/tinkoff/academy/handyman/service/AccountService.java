package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.data.PaymentSystemStatistic;
import ru.tinkoff.academy.handyman.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public List<PaymentSystemStatistic> getPaymentSystemStatistics() {
        return repository.getPaymentSystemStatistics();
    }
}
