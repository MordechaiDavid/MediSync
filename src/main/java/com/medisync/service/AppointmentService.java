package com.medisync.service;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import com.medisync.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;



    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


}
