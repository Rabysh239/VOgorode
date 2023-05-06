package ru.tinkoff.academy.landscape.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.landscape.dto.UserBodyDto;
import ru.tinkoff.academy.landscape.dto.UserDto;
import ru.tinkoff.academy.landscape.model.User;
import ru.tinkoff.academy.landscape.model.UserType;
import ru.tinkoff.academy.landscape.repository.UserTypeRepository;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;
    private final UserTypeRepository userTypeRepository;

    public User mapToEntity(UserBodyDto userBodyDto) {
        User user = modelMapper.map(userBodyDto, User.class);
        UserType userType = userTypeRepository.getReferenceByName(userBodyDto.getType());
        user.setTypeId(userType.getId());
        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserType userType = userTypeRepository.getReferenceById(user.getTypeId());
        userDto.setType(userType.getName());
        return userDto;
    }
}
