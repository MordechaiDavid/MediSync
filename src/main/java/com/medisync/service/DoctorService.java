package com.medisync.service;

import com.medisync.entity.Doctor;
import com.medisync.repository.DoctorRepository;
import com.medisync.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    // FIXME right now we can create unstop doctor with same details (what is the diff key, id generate auto)
    // TODO check if try to create doctor that already exist
    public Doctor create(Doctor doctor) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        doctor.setPassword(encoder.encode(doctor.getPassword()));
        // check: encoder.matches("myPassword", result)
        Doctor savedDoctor = repository.save(doctor);
        log.info("create new doctor with id " + savedDoctor.getId());
        return savedDoctor;
    }

    public List<Doctor> getAll() {
        return repository.findAll();
    }

    public Optional<Doctor> getById(Long id) {
        return repository.findById(id);
    }

    public Doctor update(Doctor doctor, Long id) {
        Doctor existingDoctor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException()); // FIXME what do we do with the exception?
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setLastName(doctor.getLastName());
        existingDoctor.setTitle(doctor.getTitle());
        existingDoctor.setLicenseNumber(doctor.getLicenseNumber());
        Doctor updatedDoctor = repository.save(existingDoctor);
        log.info("updated doctor with id: " + id);
        return updatedDoctor;
    }

    public void delete(Long id) {
        repository.deleteById(id);
        log.info("doctor with id " + id + " deleted");
    }


}

