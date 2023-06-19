package ru.tinkoff.academy.handyman.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.handyman.dto.*;
import ru.tinkoff.academy.handyman.model.Account;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.Skill;
import ru.tinkoff.academy.handyman.model.User;

@Component
@RequiredArgsConstructor
public class HandymanMapper {
    private final ModelMapper modelMapper;
    private static final String USER_TYPE = "handyman";

    public Handyman mapToEntity(UpdatingHandymanDto updatingHandymanDto) {
        Handyman handyman = modelMapper.map(updatingHandymanDto, Handyman.class);
        handyman.setPhoto(Base64.decodeBase64(updatingHandymanDto.getPhoto()));
        return handyman;
    }

    public HandymanDto mapToDto(Handyman handyman, User user) {
        HandymanDto handymanDto = modelMapper.map(handyman, HandymanDto.class);
        handymanDto.setLogin(user.getLogin());
        handymanDto.setFirstName(user.getFirstName());
        handymanDto.setLastName(user.getLastName());
        handymanDto.setEmail(user.getEmail());
        handymanDto.setPhone(user.getPhone());
        handymanDto.setLatitude(user.getLatitude());
        handymanDto.setLongitude(user.getLongitude());
        handymanDto.setPhoto(Base64.encodeBase64String(handyman.getPhoto()));
        return handymanDto;
    }

    public UserDto mapToUserDto(UpdatingHandymanDto updatingHandymanDto) {
        UserDto userDto = modelMapper.map(updatingHandymanDto, UserDto.class);
        userDto.setType(USER_TYPE);
        return userDto;
    }

    public Account mapToEntity(InnerAccountDto innerAccountDto) {
        return modelMapper.map(innerAccountDto, Account.class);
    }

    public Skill mapToEntity(InnerSkillDto innerSkillDto) {
        return modelMapper.map(innerSkillDto, Skill.class);
    }
}
