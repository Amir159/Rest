package com.syncretis.controller;

import com.syncretis.dto.UserDto;
import com.syncretis.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated()
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    UserDto getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PutMapping("{id}")
    UserDto updateUser(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid UserDto newUserDto) {
        return userService.put(id, newUserDto);
    }

    @PostMapping
    UserDto newUser(@RequestBody @Valid UserDto newUserDto) {
        return userService.save(newUserDto);
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable("id") @Min(1) Long id) {
        userService.deleteById(id);
    }
}
