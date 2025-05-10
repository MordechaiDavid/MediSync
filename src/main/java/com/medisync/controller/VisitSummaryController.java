package com.medisync.controller;

import com.medisync.dto.request.create.VisitSummaryCreateDto;
import com.medisync.dto.response.VisitSummaryResponseDto;
import com.medisync.entity.VisitSummary;
import com.medisync.service.StorageService;
import com.medisync.service.VisitSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/visit-summary")
public class VisitSummaryController {


    @Autowired
    private VisitSummaryService vsService;
    @Autowired
    private StorageService storageService;

    @PostMapping("/create")
    public ResponseEntity<VisitSummaryResponseDto> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("patientId") Long patientId
    )
    {
        String fileName = storageService.uploadFile(file, "visit-summaries/");
        VisitSummary vs = vsService.create(VisitSummary.builder().fileName(fileName).doctorId(doctorId).patientId(patientId).build());
        return ResponseEntity.ok(VisitSummaryResponseDto.fromVisitSummary(vs));
    }


}
