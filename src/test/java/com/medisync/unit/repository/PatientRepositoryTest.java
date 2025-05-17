package com.medisync.unit.repository;

import com.medisync.entity.Patient;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.repository.PatientRepository;
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
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Test 1: save Patient")
    @Order(1)
    @Rollback(value = false)
    public void savePatientTest(){
        Patient patient = Patient.builder()
                .dateOfBirth(LocalDate.of(1997, 3, 28))
                .gender(Gender.MALE)
                .firstName("Moti")
                .lastName("David")
                .phone("0533333333")
                .insuranceInfo(InsuranceInfo.MACABI).build();
        System.out.println(patientRepository.save(patient));
        Assertions.assertThat(patient.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getPatientTest(){
        Patient patient = patientRepository.findById(1L).get();
        System.out.println(patient);
        Assertions.assertThat(patient.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfPatientsTest(){
        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);
        Assertions.assertThat(patients.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updatePatientTest(){
        Patient patient = patientRepository.findById(1L).get();
        patient.setLastName("Levi");
        Patient patientUpdated = patientRepository.save(patient);
        System.out.println(patientUpdated);
        Assertions.assertThat(patientUpdated.getLastName()).isEqualTo("Levi");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        patientRepository.deleteById(1L);
        Optional<Patient> patientOptional = patientRepository.findById(1L);
        Assertions.assertThat(patientOptional).isEmpty();
    }
}
