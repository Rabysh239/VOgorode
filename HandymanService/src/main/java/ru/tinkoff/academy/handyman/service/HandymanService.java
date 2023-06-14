package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.dto.CreatingHandymanDto;
import ru.tinkoff.academy.handyman.dto.HandymanDto;
import ru.tinkoff.academy.handyman.dto.UpdatingHandymanDto;
import ru.tinkoff.academy.handyman.dto.UserDto;
import ru.tinkoff.academy.handyman.exception.EntityNotFoundException;
import ru.tinkoff.academy.handyman.mapper.HandymanMapper;
import ru.tinkoff.academy.handyman.model.Account;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.Skill;
import ru.tinkoff.academy.handyman.model.User;
import ru.tinkoff.academy.handyman.repository.HandymanRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HandymanService {
    private final HandymanRepository repository;
    private final HandymanMapper mapper;
    private final UserService userService;

    @Transactional
    public HandymanDto create(CreatingHandymanDto creatingHandymanDto) {
        UserDto userDto = mapper.mapToUserDto(creatingHandymanDto);
        User user = userService.create(userDto);
        Handyman handyman = mapper.mapToEntity(creatingHandymanDto);
        handyman.setUserId(user.getId());
        List<Account> accounts = creatingHandymanDto.getAccounts().stream().map(mapper::mapToEntity).toList();
        List<Skill> skills = creatingHandymanDto.getSkills().stream().map(mapper::mapToEntity).toList();
        handyman.setAccounts(accounts);
        handyman.setSkills(skills);
        Handyman savedHandyman = repository.save(handyman);
        return mapper.mapToDto(savedHandyman, user);
    }

    public HandymanDto get(Long id) {
        Handyman handyman = getHandyman(id);
        User user = userService.get(handyman.getUserId());
        return mapper.mapToDto(handyman, user);
    }

    public HandymanDto update(Long id, UpdatingHandymanDto updatingHandymanDto) {
        Handyman handyman = getHandyman(id);
        Handyman updatedHandyman = mapper.mapToEntity(updatingHandymanDto);
        handyman.setPhoto(updatedHandyman.getPhoto());
        repository.save(handyman);
        UserDto userDto = mapper.mapToUserDto(updatingHandymanDto);
        User user = userService.update(handyman.getUserId(), userDto);
        return mapper.mapToDto(handyman, user);
    }

    public void delete(Long id) {
        Handyman handyman = getHandyman(id);
        userService.delete(handyman.getUserId());
        repository.deleteById(id);
    }

    private Handyman getHandyman(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("handyman", id));
    }
}
