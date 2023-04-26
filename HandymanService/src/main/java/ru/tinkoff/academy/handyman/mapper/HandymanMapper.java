package ru.tinkoff.academy.handyman.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.handyman.dto.HandymanBodyDto;
import ru.tinkoff.academy.handyman.dto.HandymanDto;
import ru.tinkoff.academy.handyman.dto.UserBodyDto;
import ru.tinkoff.academy.handyman.model.Handyman;
import ru.tinkoff.academy.handyman.model.User;

@Component
@RequiredArgsConstructor
public class HandymanMapper {
    private final ModelMapper modelMapper;
    private static final String USER_TYPE = "handyman";

    public Handyman mapToEntity(HandymanBodyDto handymanBodyDto, User user) {
        Handyman handyman = modelMapper.map(handymanBodyDto, Handyman.class);
        handyman.setUserId(user.getId());
        return handyman;
    }

    public HandymanDto mapToDto(Handyman handyman, User user) {
        HandymanDto handymanDto = modelMapper.map(handyman, HandymanDto.class);
        handymanDto.setLogin(user.getLogin());
        handymanDto.setEmail(user.getEmail());
        handymanDto.setPhone(user.getPhone());
        return handymanDto;
    }

    public UserBodyDto mapToUserBodies(HandymanBodyDto handymanBodyDto) {
        UserBodyDto userBodyDto = modelMapper.map(handymanBodyDto, UserBodyDto.class);
        userBodyDto.setType(USER_TYPE);
        return userBodyDto;
    }
}
