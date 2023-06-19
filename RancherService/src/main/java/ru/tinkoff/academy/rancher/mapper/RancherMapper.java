package ru.tinkoff.academy.rancher.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UpdatingRancherDto;
import ru.tinkoff.academy.rancher.dto.UserDto;
import ru.tinkoff.academy.rancher.model.Rancher;
import ru.tinkoff.academy.rancher.model.User;

@Component
@RequiredArgsConstructor
public class RancherMapper {
    private final ModelMapper modelMapper;
    private static final String USER_TYPE = "rancher";

    public Rancher mapToEntity(UpdatingRancherDto updatingRancherDto) {
        return modelMapper.map(updatingRancherDto, Rancher.class);
    }

    public RancherDto mapToDto(Rancher rancher, User user) {
        RancherDto rancherDto = modelMapper.map(rancher, RancherDto.class);
        rancherDto.setLogin(user.getLogin());
        rancherDto.setFirstName(user.getFirstName());
        rancherDto.setLastName(user.getLastName());
        rancherDto.setEmail(user.getEmail());
        rancherDto.setPhone(user.getPhone());
        rancherDto.setLatitude(user.getLatitude());
        rancherDto.setLongitude(user.getLongitude());
        return rancherDto;
    }

    public UserDto mapToUserDto(UpdatingRancherDto updatingRancherDto) {
        UserDto userDto = modelMapper.map(updatingRancherDto, UserDto.class);
        userDto.setType(USER_TYPE);
        return userDto;
    }
}
