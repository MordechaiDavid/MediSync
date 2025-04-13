package com.medisync.controller;

import com.medisync.dto.request.UserCreateDto;
import com.medisync.dto.request.UserUpdateDto;
import com.medisync.dto.response.UserResponseDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import com.medisync.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto dto) {
        User userCreated = userService.create(User.fromUserDto(dto));
        return ResponseEntity.ok(UserResponseDto.fromUser(userCreated));
    }
    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserUpdateDto dto){
        User userUpdated = userService.update(User.fromUserDto(dto));
        return ResponseEntity.ok(UserResponseDto.fromUser(userUpdated));
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
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.getAll();
        List<UserResponseDto> dtos = EntityMapper.convertList(users, UserResponseDto::fromUser);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/get-by-email")
    public ResponseEntity<UserResponseDto> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(UserResponseDto.fromUser(userService.getByEmail(email)));
    }


}

