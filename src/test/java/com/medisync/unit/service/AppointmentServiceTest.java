package com.medisync.unit.service;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.repository.AppointmentRepository;
import com.medisync.service.AppointmentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentServiceTest {

    Appointment appointment;
    @Mock
    private AppointmentRepository appointmentRepository;
    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        appointment = Appointment.builder()
                .id(1L)
                .appointmentDate(LocalDateTime.of(2015, 7, 20, 10, 30))
                .doctorId(1L)
                .patientId(1L)
                .status(AppointmentStatus.SCHEDULED)
                .build();
    }

    @Test
    @Order(1)
    public void createTest() {
        given(appointmentRepository.save(appointment)).willReturn(appointment);

        Appointment savedAppointment = appointmentService.create(appointment);

        System.out.println(savedAppointment);
        assertThat(savedAppointment).isNotNull();
    }

    @Test
    @Order(2)
    public void getById() {
        given(appointmentRepository.findById(1L)).willReturn(Optional.of(appointment));

        Appointment existingAppointment = appointmentService.getById(appointment.getId()).get();

        System.out.println(existingAppointment);
        assertThat(existingAppointment).isNotNull();
    }

    @Test
    @Order(3)
    public void getAll() {
        Appointment anotherAppointment = Appointment.builder()
                .id(2L)
                .appointmentDate(LocalDateTime.of(2015, 8, 4, 10, 30))
                .doctorId(1L)
                .patientId(7L)
                .status(AppointmentStatus.SCHEDULED)
                .build();

        given(appointmentRepository.findAll()).willReturn(List.of(appointment, anotherAppointment));

        List<Appointment> appointmentList = appointmentService.getAll();

        System.out.println(appointmentList);
        assertThat(appointmentList).isNotNull();
        assertThat(appointmentList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void update() {

        given(appointmentRepository.findById(appointment.getId())).willReturn(Optional.of(appointment));
        appointment.setPatientId(3L);
        appointment.setDoctorId(8L);
        given(appointmentRepository.save(appointment)).willReturn(appointment);

        Appointment updatedAppointment = appointmentService.update(appointment, appointment.getId());

        System.out.println(updatedAppointment);
        assertThat(updatedAppointment.getDoctorId()).isEqualTo(8L);
        assertThat(updatedAppointment.getPatientId()).isEqualTo(3L);
    }

    @Test
    @Order(5)
    public void delete() {

        willDoNothing().given(appointmentRepository).deleteById(appointment.getId());

        appointmentService.delete(appointment.getId());

        verify(appointmentRepository, times(1)).deleteById(appointment.getId());
    }

}
