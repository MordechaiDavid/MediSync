package com.medisync.repository;

import com.medisync.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {



}
