package com.medisync.unit.service;

import com.medisync.entity.Doctor;
import com.medisync.enums.DoctorTitle;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.repository.DoctorRepository;
import com.medisync.service.DoctorService;
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
public class DoctorServiceTest {

    Doctor doctor;
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    public void setup() {
        doctor = Doctor.builder()
                .id(1L)
                .firstName("Moti")
                .lastName("David")
                .title(DoctorTitle.Dr)
                .email("moti@gmail")
                .licenseNumber("3344343434")
                .specialization(MedicalSpecialization.ENDOCRINOLOGY)
                .password("dsfsdfqwv9983")
                .phone("0533333333")
                .build();
    }

    @Test
    @Order(1)
    public void createTest() {
        given(doctorRepository.save(doctor)).willReturn(doctor);

        Doctor savedDoctor = doctorService.create(doctor);

        System.out.println(savedDoctor);
        assertThat(savedDoctor).isNotNull();
    }

    @Test
    @Order(2)
    public void getById() {
        given(doctorRepository.findById(1L)).willReturn(Optional.of(doctor));

        Doctor existingDoctor = doctorService.getById(doctor.getId()).get();

        System.out.println(existingDoctor);
        assertThat(existingDoctor).isNotNull();
    }

    @Test
    @Order(3)
    public void getAll() {
        Doctor anotherDoctor = Doctor.builder()
                .specialization(MedicalSpecialization.ENDOCRINOLOGY)
                .licenseNumber("99939j88")
                .title(DoctorTitle.Prof)
                .password("iv8vjd9")
                .id(2L)
                .lastName("Azran")
                .firstName("Ohad")
                .phone("0544444444")
                .build();

        given(doctorRepository.findAll()).willReturn(List.of(doctor, anotherDoctor));

        List<Doctor> doctorList = doctorService.getAll();

        System.out.println(doctorList);
        assertThat(doctorList).isNotNull();
        assertThat(doctorList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void update() {

        given(doctorRepository.findById(doctor.getId())).willReturn(Optional.of(doctor));
        doctor.setEmail("max@gmail.com");
        doctor.setFirstName("Max");
        given(doctorRepository.save(doctor)).willReturn(doctor);

        Doctor updatedDoctor = doctorService.update(doctor, doctor.getId());

        System.out.println(updatedDoctor);
        assertThat(updatedDoctor.getEmail()).isEqualTo("max@gmail.com");
        assertThat(updatedDoctor.getFirstName()).isEqualTo("Max");
    }

    @Test
    @Order(5)
    public void delete() {

        willDoNothing().given(doctorRepository).deleteById(doctor.getId());

        doctorService.delete(doctor.getId());

        verify(doctorRepository, times(1)).deleteById(doctor.getId());
    }

}
