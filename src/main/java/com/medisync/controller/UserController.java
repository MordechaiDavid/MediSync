package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
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


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        try {
            User userCreated = userService.create(User.fromUserDto(dto));
            return ResponseEntity.ok(UserDto.fromUser(userCreated));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, String password){
        boolean isAuthenticated = userService.authenticate(email, password);
        if (isAuthenticated)
            return ResponseEntity.ok("Login successful");
        else {
            return ResponseEntity.internalServerError().body("Invalid credentials");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        try {
            List<UserDto> dtos = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                dtos.add(i, UserDto.fromUser(users.get(i)));
            }
            return ResponseEntity.ok(dtos);
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/get-user-by-email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        try {
            Optional<User> user = userService.getUserByEmail(email);
            return user.isPresent() ? ResponseEntity.ok(UserDto.fromUser(user.get())) : null;
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/get-user-by-id-number")
    public ResponseEntity<UserDto> getUserByIdNumber(@RequestParam String idNumber) {
        try {
            Optional<User> user = userService.getUserByIdNumber(idNumber);
            return user.isPresent() ? ResponseEntity.ok(UserDto.fromUser(user.get())) : null;
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

