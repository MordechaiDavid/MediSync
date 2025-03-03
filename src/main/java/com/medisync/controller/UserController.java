package com.medisync.controller;

import com.medisync.dto.UserDto;
import com.medisync.entity.User;
import com.medisync.exception.UserNotFoundException;
import com.medisync.response.ApiResponse;
import com.medisync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        try {
            List<UserDto> dtos = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                dtos.add(i, UserDto.fromUser(users.get(i)));
            }
            ApiResponse<List<UserDto>> response = new ApiResponse<>(
                    200,
                    "getting all users",
                    dtos
            );
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            ApiResponse<List<UserDto>> response = new ApiResponse<>(
                    500,
                    "Internal server error while fetching users",
                    e.getMessage()
            );
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/get-user-by-email")
    public ResponseEntity<ApiResponse<UserDto>> getUserByEmail(@RequestParam String email) {
        try {
            User user = userService.getUserByEmail(email);
            ApiResponse<UserDto> response = new ApiResponse<>(
                    200,
                    "User found successfully",
                    UserDto.fromUser(user)
            );
            return ResponseEntity.ok(response);
        }catch (UserNotFoundException e){
            ApiResponse<UserDto> response = new ApiResponse<>(
                    404,
                    "User not found with ID number: " + email,
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (RuntimeException e){
            ApiResponse<UserDto> response = new ApiResponse<>(
                    500,
                    "Internal server error while fetching user",
                    e.getMessage()
            );
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/get-user-by-id-number")
    public ResponseEntity<ApiResponse<UserDto>> getUserByIdNumber(@RequestParam String idNumber) {
        try {
            User user = userService.getUserByIdNumber(idNumber);
            ApiResponse<UserDto> response = new ApiResponse<>(
                    200,
                    "User found successfully",
                    UserDto.fromUser(user)
            );
            return ResponseEntity.ok(response);
        }catch (UserNotFoundException e){
            ApiResponse<UserDto> response = new ApiResponse<>(
                    404,
                    "User not found with ID number: " + idNumber,
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (RuntimeException e){
            ApiResponse<UserDto> response = new ApiResponse<>(
                    500,
                    "Internal server error while fetching user",
                    e.getMessage()
            );
            return ResponseEntity.internalServerError().body(response);
        }

    }
}

