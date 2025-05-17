package com.medisync.controller;

import com.medisync.dto.request.create.DoctorCreateDto;
import com.medisync.dto.response.DoctorResponseDto;
import com.medisync.entity.Doctor;
import com.medisync.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponseDto create(@RequestBody DoctorCreateDto dto) {
        Doctor doctor = service.create(Doctor.fromDto(dto));
        return DoctorResponseDto.fromDoctor(doctor);
    }

    @GetMapping
    public List<DoctorResponseDto> getAll() {
        List<Doctor> doctors = service.getAll();
        return doctors.stream().map(DoctorResponseDto::fromDoctor).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Doctor>> getById(@PathVariable Long id) {
        Optional<Doctor> optionalDoctor = service.getById(id);
        optionalDoctor.ifPresent(doctor -> doctor.setPassword(""));
        return new ResponseEntity<Optional<Doctor>>(optionalDoctor, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor doctor) {
        return new ResponseEntity<Doctor>(service.update(doctor, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>("Doctor deleted successfully with id: " + id, HttpStatus.OK);
    }

}

