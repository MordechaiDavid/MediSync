package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
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
        try {
            User userCreated = userService.create(User.fromUserDto(dto));
            return ResponseEntity.ok(UserDto.fromUser(userCreated));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, String password){
        String token = userService.authenticateAndGenerateToken(email, password);
        if (token != null)
            return ResponseEntity.ok(token);
        else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(HttpServletRequest request) {
        String emailFromToken = (String) request.getAttribute("email");
        if (emailFromToken == null){
            return ResponseEntity.status(401).build();
        }
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
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email, HttpServletRequest request) {
        String emailFromToken = (String) request.getAttribute("email");
        if (emailFromToken == null ){
            return ResponseEntity.status(401).build();
        }
        if (!email.equals(emailFromToken)){
            return ResponseEntity.status(403).build();
        }
        try {
            Optional<User> user = userService.getUserByEmail(email);
            return user.isPresent() ? ResponseEntity.ok(UserDto.fromUser(user.get())) : null;
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/get-user-by-identity-number")
    public ResponseEntity<UserDto> getUserByIdentityNumber(@RequestParam String identityNumber, HttpServletRequest request) {
        String emailFromToken = (String) request.getAttribute("email");
        if (emailFromToken == null){
            return ResponseEntity.status(401).build();
        }
        try {
            Optional<User> user = userService.getUserByIdentityNumber(identityNumber);
            if (user.isPresent()){
                if (!user.get().getEmail().equals(emailFromToken)){
                    return ResponseEntity.status(403).build();
                }
                return ResponseEntity.ok(UserDto.fromUser(user.get()));
            }
            return ResponseEntity.notFound().build();
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

