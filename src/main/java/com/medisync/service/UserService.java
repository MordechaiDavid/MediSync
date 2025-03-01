package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.entity.Patient;
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
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    public User create(User user) {
        User newUser = userRepository.save(user);
        if (user.getRole().equals(UserType.DOCTOR)){
            doctorService.create(new Doctor(newUser.getId()));
        } else if (user.getRole().equals(UserType.PATIENT)) {
            patientService.create(new Patient(newUser.getId()));
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

