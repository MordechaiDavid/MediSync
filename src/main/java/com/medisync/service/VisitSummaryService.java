package com.medisync.service;

import com.medisync.entity.Patient;
import com.medisync.entity.VisitSummary;
import com.medisync.repository.VisitSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitSummaryService {

    @Autowired
    private VisitSummaryRepository vsRepository;
    @Autowired
    private StorageService storageService;

    public VisitSummary create(VisitSummary vs) {

        return vsRepository.save(vs);
    }

    public String getFileNameById(Long id){
        Optional<VisitSummary> vs = vsRepository.findById(id);
        String fileName = vs.isPresent() ? vs.get().getFileName() : null;
        return "visit-summaries/" + fileName;
    }
}
