package com.medisync.service;

import com.medisync.entity.Appointment;
import com.medisync.entity.Appointment;
import com.medisync.entity.User;
import com.medisync.enums.AppointmentStatus;
import com.medisync.repository.AppointmentRepository;
import com.medisync.repository.AppointmentRepository;
import com.medisync.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    // FIXME right now we can create unstop appointment with same details (what is the diff key, id generate auto)
    // TODO check if try to create appointment that already exist
    public Appointment create(Appointment appointment) {
        Appointment savedAppointment = repository.save(appointment);
        log.info("create new appointment with id " + savedAppointment.getId());
        return savedAppointment;
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }

    public Optional<Appointment> getById(Long id) {
        return repository.findById(id);
    }

    public Appointment update(Appointment appointment, Long id) {
        Appointment existingAppointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException()); // FIXME what do we do with the exception?
        existingAppointment.setPatientId(appointment.getPatientId());
        existingAppointment.setStatus(appointment.getStatus());
        existingAppointment.setDoctorId(appointment.getDoctorId());
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        Appointment updatedAppointment = repository.save(existingAppointment);
        log.info("updated appointment with id: " + id);
        return updatedAppointment;
    }

    public void delete(Long id) {
        repository.deleteById(id);
        log.info("appointment with id " + id + " deleted");
    }


}
