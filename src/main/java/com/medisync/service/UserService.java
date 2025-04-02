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
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    @Transactional
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        if (user.getRole().equals(UserType.DOCTOR)){
            doctorService.create(new Doctor(user.getId()));
        }else if (user.getRole().equals(UserType.PATIENT)) {
            patientService.create(new Patient(user.getId()));
        }
        return user;
    }

    public String authenticateAndGenerateToken(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user != null && encoder.matches(password, user.getPassword())){
            return JwtUtil.generateToken(email);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    public User getUserByIdentityNumber(String identityNumber) {
        return userRepository.findByIdentityNumber(identityNumber);
    }
}

