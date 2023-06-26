package ru.tinkoff.academy.handyman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.academy.handyman.dto.*;
import ru.tinkoff.academy.handyman.exception.EntityNotFoundException;
import ru.tinkoff.academy.handyman.mapper.HandymanMapper;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.Skill;
import ru.tinkoff.academy.handyman.model.User;
import ru.tinkoff.academy.handyman.repository.HandymanRepository;
import ru.tinkoff.academy.handyman.repository.SkillRepository;

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
        Handyman handyman = mapper.mapToEntity(creatingHandymanDto);
        handyman.setUserId(user.getId());
        Handyman savedHandyman = repository.save(handyman);
        creatingHandymanDto.getAccounts().forEach(f -> accountService.create(f, savedHandyman.getId()));
        creatingHandymanDto.getSkills().forEach(s -> create(s, savedHandyman));
        return mapper.mapToDto(savedHandyman, user);
    }

    public HandymanDto get(String id) {
        Handyman handyman = getHandyman(id);
        User user = userService.get(handyman.getUserId());
        return mapper.mapToDto(handyman, user);
    }

    public HandymanDto update(String id, UpdatingHandymanDto updatingHandymanDto) {
        Handyman handyman = getHandyman(id);
        Handyman updatedHandyman = mapper.mapToEntity(updatingHandymanDto);
        handyman.setPhoto(updatedHandyman.getPhoto());
        repository.save(handyman);
        UserDto userDto = mapper.mapToUserDto(updatingHandymanDto);
        User user = userService.update(handyman.getUserId(), userDto);
        return mapper.mapToDto(handyman, user);
    }

    public void delete(String id) {
        Handyman handyman = getHandyman(id);
        userService.delete(handyman.getUserId());
        repository.deleteById(id);
    }

    private Skill create(InnerSkillDto innerSkillDto, Handyman handyman) {
        var skill = new Skill();
        skill.setHandyman(handyman);
        skill.setName(innerSkillDto.getName());
        Skill savedSkill = skillRepository.save(skill);
        handyman.getSkills().add(savedSkill);
        repository.save(handyman);
        return skillRepository.save(savedSkill);
    }

    private Handyman getHandyman(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("handyman", id));
    }
}
