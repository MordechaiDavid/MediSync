package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.entity.Patient;
import com.medisync.entity.User;
import com.medisync.enums.UserType;
import com.medisync.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

