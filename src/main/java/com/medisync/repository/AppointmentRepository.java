package com.medisync.repository;

import com.medisync.entity.Appointment;
import com.medisync.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{

}
