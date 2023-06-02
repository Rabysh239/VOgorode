package ru.tinkoff.academy.landscape.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.landscape.dto.CreateUserDto;
import ru.tinkoff.academy.landscape.model.User;
import ru.tinkoff.academy.landscape.model.UserType;
import ru.tinkoff.academy.landscape.repository.UserTypeRepository;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;
    private final UserTypeRepository userTypeRepository;

    public User mapToEntity(CreateUserDto createUserDto) {
        User user = modelMapper.map(createUserDto, User.class);
        UserType userType = userTypeRepository.getReferenceByName(createUserDto.getType());
        user.setType(userType);
        return user;
    }
}
