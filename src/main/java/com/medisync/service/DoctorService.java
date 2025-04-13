package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.entity.User;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public Doctor create(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }


}
