package com.medisync.controller;

import com.medisync.dto.response.DoctorDto;
import com.medisync.entity.Doctor;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.service.DoctorService;
import com.medisync.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAll(){
        List<Doctor> doctors = doctorService.getAll();
        List<DoctorDto> dtos = EntityMapper.convertList(doctors, DoctorDto::fromDoctor);
        return ResponseEntity.ok(dtos);
    }


}
