package com.medisync.service;

import com.medisync.entity.User;
import com.medisync.enums.UserType;
import com.medisync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
//        if (userCreated.getRole().equals(UserType.DOCTOR)){
//            doctorService.create();
//        }
        return userRepository.save(user);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

