package com.medisync.controller;

import com.medisync.dto.request.create.VisitSummaryCreateDto;
import com.medisync.dto.response.VisitSummaryResponseDto;
import com.medisync.entity.VisitSummary;
import com.medisync.service.VisitSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/visit-summary")
public class VisitSummaryController {


    @Autowired
    private VisitSummaryService vsService;

//    @PostMapping("/create")
//    public ResponseEntity<VisitSummaryResponseDto> create(@ModelAttribute VisitSummaryCreateDto dto) throws IOException {
//        MultipartFile document = dto.getDocument();
//        String s3Key = s3Uploader.uploadFile(document.getInputStream(), "visit-summaries", document.getSize());
//        VisitSummary visitSummary = vsService.create(VisitSummary.fromVisitSummaryDto(dto));
//        return ResponseEntity.ok(VisitSummaryResponseDto.fromVisitSummary(visitSummary));
//    }
}
