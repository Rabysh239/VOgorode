package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.data.PaymentSystemStatistic;
import ru.tinkoff.academy.handyman.dto.InnerAccountDto;
import ru.tinkoff.academy.handyman.exception.EntityNotFoundException;
import ru.tinkoff.academy.handyman.model.Account;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.repository.AccountRepository;
import ru.tinkoff.academy.handyman.repository.HandymanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final HandymanRepository handymanRepository;
    private final ModelMapper mapper;

    public List<PaymentSystemStatistic> getPaymentSystemStatistics() {
        return repository.getPaymentSystemStatistics();
    }

    public Account create(InnerAccountDto innerAccountDto, String handymanId) {
        Account account = mapper.map(innerAccountDto, Account.class);
        Handyman handyman = getHandyman(handymanId);
        account.setHandyman(handyman);
        Account savedAccount = repository.save(account);
        handyman.getAccounts().add(savedAccount);
        handymanRepository.save(handyman);
        return savedAccount;
    }

    private Handyman getHandyman(String id) {
        return handymanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("handyman", id));
    }
}
