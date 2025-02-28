package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto dto) {
        User userCreated = userService.create(User.fromUserDto(dto));
        return UserDto.fromUser(userCreated);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-user-by-email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}

