package ru.tinkoff.academy.handyman.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.handyman.dto.HandymanDto;
import ru.tinkoff.academy.handyman.dto.UpdatingHandymanDto;
import ru.tinkoff.academy.handyman.dto.UserDto;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.User;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HandymanMapper {
    private final ModelMapper modelMapper;
    private static final String USER_TYPE = "handyman";

    public Handyman mapToEntity(UpdatingHandymanDto updatingHandymanDto, UUID userId) {
        Handyman handyman = modelMapper.map(updatingHandymanDto, Handyman.class);
        handyman.setPhoto(Base64.decodeBase64(updatingHandymanDto.getPhoto()));
        handyman.setUserId(userId);
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
}
