package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        try {
            User userCreated = userService.create(User.fromUserDto(dto));
            return ResponseEntity.ok(UserDto.fromUser(userCreated));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()){
            List<UserDto> dtos = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                dtos.add(i, UserDto.fromUser(users.get(i)));
            }
            return ResponseEntity.ok(dtos);
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

