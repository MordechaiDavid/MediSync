package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.entity.Patient;
import com.medisync.entity.User;
import com.medisync.enums.UserType;
import com.medisync.exception.UserNotFoundException;
import com.medisync.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    @Transactional
    public User create(User user) {
        logger.info("Starting user creation process for ID number: {}", user.getIdNumber());
        User newUser =  null;
        try {
            newUser = userRepository.save(user);
            logger.info("User created successfully with ID: {}", newUser.getIdNumber());
            if (user.getRole().equals(UserType.DOCTOR)){
                logger.info("Creating Doctor entity for user ID: {}", newUser.getId());
                doctorService.create(new Doctor(newUser.getId()));
                logger.info("Doctor entity created successfully for user ID: {}", newUser.getId());
            } else if (user.getRole().equals(UserType.PATIENT)) {
                logger.info("Creating Patient entity for user ID: {}", newUser.getId());
                patientService.create(new Patient(newUser.getId()));
                logger.info("Patient entity created successfully for user ID: {}", newUser.getId());
            }
        }catch (Exception e){
            logger.error("Error during user creation for ID number: {}. Details: {}", user.getIdNumber(), e.getMessage());
            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
        }
        return newUser;
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

    public User getUserByEmail(String email) {
        logger.info("Attempting to fetch user with email: {}", email);
        User user = null;
        try {
            user = userRepository.findByEmail(email);
            if (user != null){
                logger.info("Successfully fetched user for email: {}", email);
            }else {
                logger.warn("Failed to fetch user with email: {}", email);
                throw new UserNotFoundException("User not found with email: " + email);
            }
        }catch (UserNotFoundException e){
            throw e;
        }catch (Exception e){
            logger.error("Error while attempting to fetch user with email: {}. Details: {}", email, e.getMessage());
            throw new RuntimeException("Failed to fetch user by email", e);
        }
        return user;
    }

    public User getUserByIdNumber(String idNumber) {
        logger.info("Attempting to fetch user with ID number: {}", idNumber);
        try {
            User user = userRepository.findByIdNumber(idNumber);
            if (user != null){
                logger.info("Successfully fetched user for ID number: {}", idNumber);
                return user;
            }else {
                logger.warn("Failed to fetch user with ID number: {}", idNumber);
                throw new UserNotFoundException("User not found with ID number: " + idNumber);
            }
        }catch (UserNotFoundException e){
            throw e;
        }catch (Exception e){
            logger.error("Error while attempting to fetch user with ID number: {}. Details: {}", idNumber, e.getMessage());
            throw new RuntimeException("Failed to fetch user by ID number", e);
        }
    }
}

