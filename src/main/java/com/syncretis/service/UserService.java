package com.syncretis.service;

import com.syncretis.dto.UserDto;
import com.syncretis.entity.User;
import com.syncretis.exception.UsernameNotFoundException;
import com.syncretis.mapper.UserDtoMapper;
import com.syncretis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public List<UserDto> getAll() {
        return userDtoMapper.mapUsers(userRepository.findAll());
    }

    public UserDto getById(Long id) {
        return userDtoMapper.mapUser(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(HttpStatus.NOT_FOUND)));
    }

    public UserDto put(Long id, UserDto userDto) {
        User user = userDtoMapper.mapUserDto(userDto);
        if (userRepository.existsById(id)) {
            User newUser = userRepository.findById(id).orElse(null);
            user.setId(newUser.getId());
            user.setName(newUser.getName());
        }
        userRepository.save(user);
        return userDtoMapper.mapUser(user);
    }

    public UserDto save(UserDto userDto) {
        User user = userDtoMapper.mapUserDto(userDto);
        userRepository.save(user);
        return userDtoMapper.mapUser(user);
    }

    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(HttpStatus.NOT_FOUND));
        userRepository.deleteById(id);
    }
}
