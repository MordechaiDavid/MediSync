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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment update(Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        appointmentOptional.ifPresent(currentAppointment -> copyProperties(appointment, currentAppointment));
        return appointmentRepository.save(appointmentOptional.get());
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getNextAvailableForDoctor(Long doctorId){
        return appointmentRepository.findAvailableByDoctorId(
                doctorId, AppointmentStatus.AVAILABLE, LocalDateTime.now()
        );
    }

    public List<Appointment> getByPatientId(Long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }

    public String f(){
        return "some";
    }

    public void copyProperties(Appointment source, Appointment target){
        if (source.getStatus() != null) target.setStatus(source.getStatus());
        if (source.getDoctorId() != null) target.setDoctorId(source.getDoctorId());
        if (source.getPatientId() != null) target.setPatientId(source.getPatientId());
        if (source.getAppointmentDate() != null) target.setAppointmentDate(source.getAppointmentDate());
    }


}
