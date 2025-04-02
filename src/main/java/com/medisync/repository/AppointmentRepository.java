package com.medisync.repository;

import com.medisync.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query("SELECT a FROM Appointment a WHERE a.patientUserId = :userId")
    List<Appointment> findByPatientUserId(@Param("userId") Long userId);

}