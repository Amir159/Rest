package com.syncretis.mapper;

import com.syncretis.dto.UserDto;
import com.syncretis.entity.Role;
import com.syncretis.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDtoMapper {
    public List<UserDto> mapUsers(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(createUserDto(user));
        }
        return usersDto;
    }

    public UserDto mapUser(User user) {
        return createUserDto(user);
    }

    private UserDto createUserDto(User user) {
        if (user == null) {
            return null;
        }
        Long id = user.getId();
        String name = user.getName();
        Role role = user.getRole();
        String city = user.getCity();
        String password = user.getPassword();
        return new UserDto(id, name, role, city, password);
    }

    public User mapUserDto(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        user.setCity(userDto.getCity());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
