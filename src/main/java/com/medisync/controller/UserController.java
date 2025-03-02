package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Collections;
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null){
            return ResponseEntity.ok(users);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/get-user-by-email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user != null){
            return ResponseEntity.ok(UserDto.fromUser(user));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-user-by-id-number")
    public ResponseEntity<UserDto> getUserByIdNumber(@RequestParam String idNumber) {
        User user = userService.getUserByIdNumber(idNumber);
        if (user != null){
            return ResponseEntity.ok(UserDto.fromUser(user));
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}

