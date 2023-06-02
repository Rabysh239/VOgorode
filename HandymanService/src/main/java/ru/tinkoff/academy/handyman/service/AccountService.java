package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.dto.AccountDto;
import ru.tinkoff.academy.handyman.dto.InnerAccountDto;
import ru.tinkoff.academy.handyman.exception.EntityNotFoundException;
import ru.tinkoff.academy.handyman.mapper.AccountMapper;
import ru.tinkoff.academy.handyman.model.Account;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.repository.AccountRepository;
import ru.tinkoff.academy.handyman.repository.HandymanRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final HandymanRepository handymanRepository;

    public Account create(InnerAccountDto innerAccountDto, Long handymanId) {
        AccountDto accountDto = mapper.mapToDto(innerAccountDto, handymanId);
        return create(accountDto);
    }

    public Account create(AccountDto accountDto) {
        return update(null, accountDto);
    }

    public Account get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("account", id));
    }

    public Account update(Long id, AccountDto accountDto) {
        Account account = mapper.mapToEntity(id, accountDto);
        Long handymanId = accountDto.getHandymanId();
        Handyman handyman = getHandyman(handymanId);
        account.setHandyman(handyman);
        return repository.save(account);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Handyman getHandyman(Long id) {
        return handymanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("handyman", id));
    }
}