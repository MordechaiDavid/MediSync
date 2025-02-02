package com.medisync.service;

import com.medisync.entity.User;
import com.medisync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // יצירת משתמש חדש
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // שליפת כל המשתמשים
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // חיפוש משתמש לפי אימייל
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

