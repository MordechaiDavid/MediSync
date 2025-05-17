package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.entity.Patient;
import com.medisync.entity.User;
import com.medisync.enums.UserType;
import com.medisync.repository.UserRepository;
import com.medisync.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User userCreated = userRepository.save(user);
        return userCreated;
    }

    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        optionalUser.ifPresent(currentUser -> copyProperties(user, currentUser));
        return userRepository.save(optionalUser.get());
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }


    public void copyProperties(User source, User target){
        if (source.getName() != null) target.setName(source.getName());
        if (source.getEmail() != null) target.setEmail(source.getEmail());
    }

    public String authenticateAndGenerateToken(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user != null && encoder.matches(password, user.getPassword())){
            return JwtUtil.generateToken(email);
        }
        return null;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    public User getByIdentityNumber(String identityNumber) {
        return userRepository.findByIdentityNumber(identityNumber);
    }
}

