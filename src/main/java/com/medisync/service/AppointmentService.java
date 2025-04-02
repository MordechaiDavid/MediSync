package com.medisync.service;

import com.medisync.entity.Appointment;
import com.medisync.entity.User;
import com.medisync.enums.AppointmentStatus;
import com.medisync.repository.AppointmentRepository;
import com.medisync.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public Appointment create(Appointment appointment) {
        Appointment appointmentCreated = null;
        if (patientService.existByUserId(appointment.getPatientUserId())
                && doctorService.existByUserId(appointment.getDoctorUserId())) {
            appointmentCreated = appointmentRepository.save(appointment);
            logger.info("Appointment created for patient with user_id: {}", appointmentCreated.getPatientUserId());
        }
        return appointmentCreated;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByPatientUserId(Long userId){
        return appointmentRepository.findByPatientUserId(userId);
    }



}
