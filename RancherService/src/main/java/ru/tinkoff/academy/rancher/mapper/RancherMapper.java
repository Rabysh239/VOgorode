package ru.tinkoff.academy.rancher.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.rancher.dto.RancherBodyDto;
import ru.tinkoff.academy.rancher.dto.RancherDto;
import ru.tinkoff.academy.rancher.dto.UserBodyDto;
import ru.tinkoff.academy.rancher.model.Rancher;
import ru.tinkoff.academy.rancher.model.User;

@Component
@RequiredArgsConstructor
public class RancherMapper {
    private final ModelMapper modelMapper;
    private static final String USER_TYPE = "rancher";

    public Rancher mapToEntity(RancherBodyDto rancherBodyDto, User user) {
        Rancher rancher = modelMapper.map(rancherBodyDto, Rancher.class);
        rancher.setUserId(user.getId());
        return rancher;
    }

    public RancherDto mapToDto(Rancher rancher, User user) {
        RancherDto rancherDto = modelMapper.map(rancher, RancherDto.class);
        rancherDto.setLogin(user.getLogin());
        rancherDto.setEmail(user.getEmail());
        rancherDto.setPhone(user.getPhone());
        return rancherDto;
    }

    public UserBodyDto mapToUserBodies(RancherBodyDto rancherBodyDto) {
        UserBodyDto userBodyDto = modelMapper.map(rancherBodyDto, UserBodyDto.class);
        userBodyDto.setType(USER_TYPE);
        return userBodyDto;
    }
}
