package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import com.medisync.util.EntityMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        User userCreated = userService.create(User.fromUserDto(dto));
        return ResponseEntity.ok(UserDto.fromUser(userCreated));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, String password) {
        String token = userService.authenticateAndGenerateToken(email, password);
        if (token != null)
            return ResponseEntity.ok(token);
        else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> dtos = EntityMapper.convertList(users, UserDto::fromUser);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/get-user-by-email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(UserDto.fromUser(userService.getUserByEmail(email)));
    }

    @GetMapping("/get-user-by-identity-number")
    public ResponseEntity<UserDto> getUserByIdentityNumber(@RequestParam String identityNumber) {
        return ResponseEntity.ok(UserDto.fromUser(userService.getUserByIdentityNumber(identityNumber)));
    }

}

