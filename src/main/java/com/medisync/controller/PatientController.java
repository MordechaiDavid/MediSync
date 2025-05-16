package com.medisync.controller;

import com.medisync.entity.Patient;
import com.medisync.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@RequestBody Patient patient){
        return service.create(patient);
    }

    @GetMapping
    public List<Patient> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Patient>> getById(@PathVariable Long id){
        return new ResponseEntity<Optional<Patient>>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient){
        return new ResponseEntity<Patient>(service.update(patient, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<String>("Patient deleted successfully with id: "+ id, HttpStatus.OK );
    }

}

