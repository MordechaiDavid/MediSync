package com.medisync.controller;

import com.medisync.dto.PatientDto;
import com.medisync.entity.Patient;
import com.medisync.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping
    public PatientDto createPatient(@RequestBody PatientDto dto) {
        Patient patientCreated = patientService.create(Patient.fromPatientDto(dto));
        return PatientDto.fromPatient(patientCreated);
    }
}

