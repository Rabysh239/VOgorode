package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.dto.*;
import ru.tinkoff.academy.handyman.exception.EntityNotFoundException;
import ru.tinkoff.academy.handyman.mapper.HandymanMapper;
import ru.tinkoff.academy.handyman.model.Account;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.Skill;
import ru.tinkoff.academy.handyman.model.User;
import ru.tinkoff.academy.handyman.repository.HandymanRepository;
import ru.tinkoff.academy.handyman.repository.SkillRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HandymanService {
    private final HandymanRepository repository;
    private final HandymanMapper mapper;
    private final UserService userService;
    private final AccountService accountService;
    private final SkillRepository skillRepository;

    @Transactional
    public HandymanDto create(CreatingHandymanDto creatingHandymanDto) {
        UserDto userDto = mapper.mapToUserDto(creatingHandymanDto);
        User user = userService.create(userDto);
        Handyman handyman = mapper.mapToEntity(creatingHandymanDto, user.getId());
        Handyman savedHandyman = repository.save(handyman);
        List<Account> accounts = creatingHandymanDto.getAccounts().stream().map(f -> accountService.create(f, savedHandyman.getId())).toList();
        List<Skill> skills = creatingHandymanDto.getSkills().stream().map(s -> create(s, savedHandyman)).toList();
        savedHandyman.setAccounts(accounts);
        savedHandyman.setSkills(skills);
        return mapper.mapToDto(savedHandyman, user);
    }

    public HandymanDto get(Long id) {
        Handyman handyman = getHandyman(id);
        User user = userService.get(handyman.getUserId());
        return mapper.mapToDto(handyman, user);
    }

    public HandymanDto update(Long id, UpdatingHandymanDto updatingHandymanDto) {
        Handyman handyman = getHandyman(id);
        UserDto userDto = mapper.mapToUserDto(updatingHandymanDto);
        User user = userService.update(handyman.getUserId(), userDto);
        Handyman updatedHandyman = mapper.mapToEntity(updatingHandymanDto, user.getId());
        repository.save(updatedHandyman);
        return mapper.mapToDto(updatedHandyman, user);
    }

    public void delete(Long id) {
        Handyman handyman = getHandyman(id);
        userService.delete(handyman.getUserId());
        repository.deleteById(id);
    }

    private Skill create(InnerSkillDto innerSkillDto, Handyman handyman) {
        var skill = new Skill();
        skill.setHandyman(handyman);
        skill.setName(innerSkillDto.getName());
        return skillRepository.save(skill);
    }

    private Handyman getHandyman(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("handyman", id));
    }
}
