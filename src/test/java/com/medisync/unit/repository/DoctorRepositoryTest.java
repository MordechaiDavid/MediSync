package com.medisync.unit.repository;

import com.medisync.entity.Doctor;
import com.medisync.enums.DoctorTitle;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.repository.DoctorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Test 1: save Doctor")
    @Order(1)
    @Rollback(value = false)
    public void saveDoctorTest() {
        Doctor doctor = Doctor.builder()
                .firstName("Moti")
                .lastName("David")
                .title(DoctorTitle.Dr)
                .email("moti@gmail")
                .licenseNumber("3344343434")
                .specialization(MedicalSpecialization.ENDOCRINOLOGY)
                .password("dsfsdfqwv9983")
                .phone("0533333333")
                .build();
        System.out.println(doctorRepository.save(doctor));
        Assertions.assertThat(doctor.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getDoctorTest() {
        Doctor doctor = doctorRepository.findById(1L).get();
        System.out.println(doctor);
        Assertions.assertThat(doctor.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfDoctorsTest() {
        List<Doctor> doctors = doctorRepository.findAll();
        System.out.println(doctors);
        Assertions.assertThat(doctors.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateDoctorTest() {
        Doctor doctor = doctorRepository.findById(1L).get();
        doctor.setLastName("Levi");
        doctor.setEmail("fsdf@kd");
        doctor.setPhone("343343434343");
        doctor.setTitle(DoctorTitle.Prof);
        doctor.setLicenseNumber("939393939");
        Doctor doctorUpdated = doctorRepository.save(doctor);
        System.out.println(doctorUpdated);
        Assertions.assertThat(doctorUpdated.getLastName()).isEqualTo("Levi");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {
        doctorRepository.deleteById(1L);
        Optional<Doctor> doctorOptional = doctorRepository.findById(1L);
        Assertions.assertThat(doctorOptional).isEmpty();
    }
}
