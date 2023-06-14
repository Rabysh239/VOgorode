package ru.tinkoff.academy.handyman.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.handyman.dto.AccountDto;
import ru.tinkoff.academy.handyman.dto.InnerAccountDto;
import ru.tinkoff.academy.handyman.model.Account;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountDto mapToDto(InnerAccountDto innerAccountDto, Long handymanId) {
        AccountDto accountDto = modelMapper.map(innerAccountDto, AccountDto.class);
        accountDto.setHandymanId(handymanId);
        return accountDto;
    }

    public Account mapToEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }
}
