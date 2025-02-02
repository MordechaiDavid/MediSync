package com.medisync.controller;

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

    // API ליצירת משתמש חדש
    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // API לשליפת כל המשתמשים
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    // גכד

    // API לחיפוש לפי אימייל
    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}

