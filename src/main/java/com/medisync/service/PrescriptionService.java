package com.medisync.service;

import com.medisync.entity.Prescription;
import com.medisync.repository.PrescriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository repository;

    public Prescription create(Prescription p){
        Prescription created = repository.save(p);
        log.info("create prescription id: ");
        return created;
    }

}
