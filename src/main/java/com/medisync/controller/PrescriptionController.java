package com.medisync.controller;

import com.medisync.dto.request.create.PrescriptionCreateDto;
import com.medisync.dto.response.PrescriptionResponseDto;
import com.medisync.entity.Prescription;
import com.medisync.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService service;

    @PostMapping("/create")
    public ResponseEntity<PrescriptionResponseDto> create(@RequestBody PrescriptionCreateDto dto){
        Prescription p = service.create(Prescription.fromDto(dto));
        return ResponseEntity.ok(PrescriptionResponseDto.fromPrescription(p));
    }

}
