package com.medisync.unit.repository;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.repository.AppointmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @DisplayName("Test 1: save Appointment")
    @Order(1)
    @Rollback(value = false)
    public void saveAppointmentTest() {
        Appointment appointment = Appointment.builder()
                .appointmentDate(LocalDateTime.of(2015, 7, 20, 10, 30))
                .doctorId(1L)
                .patientId(1L)
                .status(AppointmentStatus.SCHEDULED)
                .build();
        System.out.println(appointmentRepository.save(appointment));
        Assertions.assertThat(appointment.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getAppointmentTest() {
        Appointment appointment = appointmentRepository.findById(1L).get();
        System.out.println(appointment);
        Assertions.assertThat(appointment.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfAppointmentsTest() {
        List<Appointment> appointments = appointmentRepository.findAll();
        System.out.println(appointments);
        Assertions.assertThat(appointments.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAppointmentTest() {
        Appointment appointment = appointmentRepository.findById(1L).get();
        appointment.setAppointmentDate(LocalDateTime.of(2015, 8, 20, 10, 30));
        appointment.setDoctorId(4L);
        appointment.setPatientId(5L);
        appointment.setStatus(AppointmentStatus.CANCELED);
        Appointment appointmentUpdated = appointmentRepository.save(appointment);
        System.out.println(appointmentUpdated);
        Assertions.assertThat(appointmentUpdated.getDoctorId()).isEqualTo(4L);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {
        appointmentRepository.deleteById(1L);
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(1L);
        Assertions.assertThat(appointmentOptional).isEmpty();
    }
}
