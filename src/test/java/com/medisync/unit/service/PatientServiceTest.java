package com.medisync.unit.service;

import com.medisync.entity.Patient;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.repository.PatientRepository;
import com.medisync.service.PatientService;
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
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    Patient patient;

    @BeforeEach
    public void setup(){
        patient = Patient.builder()
                .id(1L)
                .dateOfBirth(LocalDate.of(1997, 3, 28))
                .gender(Gender.MALE)
                .firstName("Moti")
                .lastName("David")
                .phone("0533333333")
                .insuranceInfo(InsuranceInfo.MACABI)
                .password("klsdfjsdkfl")
                .email("moti@com")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @Order(1)
    public void createTest(){
        given(patientRepository.save(patient)).willReturn(patient);

        Patient savedPatient = patientService.create(patient);

        System.out.println(savedPatient);
        assertThat(savedPatient).isNotNull();
    }

    @Test
    @Order(2)
    public void getById(){
        given(patientRepository.findById(1L)).willReturn(Optional.of(patient));

        Patient existingPatient = patientService.getById(patient.getId()).get();

        System.out.println(existingPatient);
        assertThat(existingPatient).isNotNull();
    }

    @Test
    @Order(3)
    public void getAll(){
        Patient anotherPatient = Patient.builder()
                .id(2L)
                .lastName("Azran")
                .firstName("Ohad")
                .gender(Gender.MALE)
                .phone("0544444444")
                .insuranceInfo(InsuranceInfo.MACABI)
                .dateOfBirth(LocalDate.of(1990, 4, 20))
                .build();

        given(patientRepository.findAll()).willReturn(List.of(patient, anotherPatient));

        List<Patient> patientList = patientService.getAll();

        System.out.println(patientList);
        assertThat(patientList).isNotNull();
        assertThat(patientList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void update(){

        given(patientRepository.findById(patient.getId())).willReturn(Optional.of(patient));
        patient.setEmail("max@gmail.com");
        patient.setFirstName("Max");
        given(patientRepository.save(patient)).willReturn(patient);

        Patient updatedPatient = patientService.update(patient, patient.getId());

        System.out.println(updatedPatient);
        assertThat(updatedPatient.getEmail()).isEqualTo("max@gmail.com");
        assertThat(updatedPatient.getFirstName()).isEqualTo("Max");
    }

    @Test
    @Order(5)
    public void delete(){

        willDoNothing().given(patientRepository).deleteById(patient.getId());

        patientService.delete(patient.getId());

        verify(patientRepository, times(1)).deleteById(patient.getId());
    }

}
