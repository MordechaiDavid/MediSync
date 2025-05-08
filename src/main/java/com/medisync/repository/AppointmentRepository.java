package com.medisync.repository;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a from Appointment a WHERE a.doctorId = :doctorId AND a.status = :status AND a.appointmentDate >= :currentTime ORDER BY a.appointmentDate ASC")
    public List<Appointment> findAvailableByDoctorId(
            @Param("doctorId") Long doctorId,
            @Param("status")AppointmentStatus status,
            @Param("currentTime")LocalDateTime currentTime
            );

    public List<Appointment> findByPatientId(Long patientId);


}