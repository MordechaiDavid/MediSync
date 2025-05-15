package com.medisync.controller;

import com.medisync.dto.request.create.UserCreateDto;
import com.medisync.dto.request.update.UserUpdateDto;
import com.medisync.dto.response.UserResponseDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import com.medisync.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto dto) {
        User userCreated = userService.create(User.fromUserDto(dto));
        return ResponseEntity.ok(UserResponseDto.fromUser(userCreated));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.getAll();
        List<UserResponseDto> dtos = EntityMapper.convertList(users, UserResponseDto::fromUser);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id){
        return new ResponseEntity<Optional<User>>(userService.getUserById(id), HttpStatus.OK);
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




}

