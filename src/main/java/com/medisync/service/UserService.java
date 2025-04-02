package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.entity.Patient;
import com.medisync.entity.User;
import com.medisync.enums.UserType;
import com.medisync.repository.UserRepository;
import com.medisync.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    @Transactional
    public User create(User user) {
        User newUser =  null;
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            newUser = userRepository.save(user);
            if (user.getRole().equals(UserType.DOCTOR)){
                doctorService.create(new Doctor(newUser.getId()));
            } else if (user.getRole().equals(UserType.PATIENT)) {
                patientService.create(new Patient(newUser.getId()));
            }
        }catch (Exception e){
            logger.error("Error during user creation for ID number: {}. Details: {}", user.getIdentityNumber(), e.getMessage());
            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
        }
        logger.info("User created successfully with ID: {}", newUser.getIdentityNumber());
        return newUser;
    }

    public String authenticateAndGenerateToken(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user != null && encoder.matches(password, user.getPassword())){
            logger.info("Successfully login for username: {}", email);
            return JwtUtil.generateToken(email);
        }
        logger.info("faild to login for email: {}", email);
        return null;
    }

    public List<User> getAllUsers() {
        logger.info("Attempting to fetch all users");
        List<User> users = null;
        try {
            users = userRepository.findAll();
            logger.info("Successfully fetched {} users", users.size());
        }catch (Exception e){
            logger.error("Failed to fetch users: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch users");
        }
        return users;
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> optionalUser;
        try {
            User user = userRepository.findByEmail(email);
            optionalUser = Optional.ofNullable(user);
        }catch (Exception e){
            logger.error("Error while attempting to fetch user with email: {}", email);
            throw new RuntimeException("Failed to fetch user by email", e);
        }
        logger.info("success fetch user by email: {}", email);
        return optionalUser;
    }

    public Optional<User> getUserByIdentityNumber(String identityNumber) {
        Optional<User> optionalUser;
        try {
            User user = userRepository.findByIdentityNumber(identityNumber);
            optionalUser = Optional.ofNullable(user);
        }catch (Exception e){
            logger.error("Error while attempting to fetch user with ID number: {}", identityNumber);
            throw new RuntimeException("Failed to fetch user by ID number", e);
        }
        logger.info("success fetch user by idNumber: {}", identityNumber);
        return optionalUser;
    }
}

