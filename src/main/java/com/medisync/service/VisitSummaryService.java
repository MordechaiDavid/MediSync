package com.medisync.service;

import com.medisync.entity.Patient;
import com.medisync.entity.VisitSummary;
import com.medisync.repository.VisitSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitSummaryService {

    @Autowired
    private VisitSummaryRepository vsRepository;

    public VisitSummary create(VisitSummary vs) {
        return vsRepository.save(vs);
    }
}
