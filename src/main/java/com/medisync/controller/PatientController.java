package com.medisync.controller;

import com.medisync.dto.request.create.PatientCreateDto;
import com.medisync.dto.response.PatientResponseDto;
import com.medisync.entity.Patient;
import com.medisync.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponseDto create(@RequestBody PatientCreateDto dto) {
        Patient patient = service.create(Patient.fromDto(dto));
        return PatientResponseDto.fromPatient(patient);
    }

    @GetMapping
    public List<PatientResponseDto> getAll() {
        List<Patient> patients = service.getAll();
        return patients.stream().map(PatientResponseDto::fromPatient).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Patient>> getById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = service.getById(id);
        optionalPatient.ifPresent(patient -> patient.setPassword(""));
        return new ResponseEntity<Optional<Patient>>(optionalPatient, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        return new ResponseEntity<Patient>(service.update(patient, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>("Patient deleted successfully with id: " + id, HttpStatus.OK);
    }

}

