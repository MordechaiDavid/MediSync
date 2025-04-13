package com.medisync.service;

import com.medisync.entity.Patient;
import com.medisync.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

}

