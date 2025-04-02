package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor create(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public boolean existByUserId(Long userId){
        return doctorRepository.existsById(userId);
    }

}
