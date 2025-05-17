package com.medisync.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.controller.PatientController;
import com.medisync.dto.request.create.PatientCreateDto;
import com.medisync.dto.response.PatientResponseDto;
import com.medisync.entity.Patient;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.service.PatientService;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientControllerTest {

    PatientCreateDto patientCreateDto;
    Patient patient;

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PatientService patientService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // for post tests
        patientCreateDto = PatientCreateDto.builder()
                .dateOfBirth(LocalDate.of(1997, 3, 28))
                .gender(Gender.MALE)
                .firstName("Moti")
                .lastName("David")
                .phone("0533333333")
                .insuranceInfo(InsuranceInfo.MACABI)
                .password("klsdfjsdkfl")
                .email("moti@com")
                .build();

        // for get tests
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
    public void createPatientTest() throws Exception {
        Patient p = Patient.fromDto(patientCreateDto);
        p.setId(1L);
        p.setCreatedAt(LocalDateTime.now());
        given(patientService.create(any(Patient.class))).willReturn(p);
        ResultActions response = mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientCreateDto)));
        response.andDo(print()).
                andExpect(jsonPath("$.firstName",
                        is(PatientResponseDto.fromPatient(p).getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(PatientResponseDto.fromPatient(p).getLastName())))
                .andExpect(jsonPath("$.phone",
                        is(PatientResponseDto.fromPatient(p).getPhone())))
                .andExpect(jsonPath("$.email",
                        is(PatientResponseDto.fromPatient(p).getEmail())));
//                .andExpect(jsonPath("$.insuranceInfo",
//                        is(PatientResponseDto.fromPatient(patient).getInsuranceInfo())))
//                .andExpect(jsonPath("$.gender",
//                        is(PatientResponseDto.fromPatient(patient).getGender())))
//                .andExpect(jsonPath("$.dateOfBirth",
//                        is(PatientResponseDto.fromPatient(patient).getDateOfBirth())))
//                .andExpect(jsonPath("$.id",
//                        is(PatientResponseDto.fromPatient(patient).getId())));
    }

    @Test
    @Order(2)
    public void getAllPatientTest() throws Exception {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        patients.add(Patient.builder()
                .id(2L)
                .lastName("Azran")
                .firstName("Ohad")
                .gender(Gender.MALE)
                .phone("0544444444")
                .insuranceInfo(InsuranceInfo.MACABI)
                .dateOfBirth(LocalDate.of(1990, 4, 20))
                .build());
        given(patientService.getAll()).willReturn(patients);
        ResultActions response = mockMvc.perform(get("/api/patients"));
        response.andExpect(status().isOk()).
                andExpect(jsonPath("$.size()",
                        is((int) patients.stream().map(PatientResponseDto::fromPatient).count())))
                .andDo(print());

    }

    @Test
    @Order(3)
    public void getByIdTest() throws Exception{
        given(patientService.getById(patient.getId())).willReturn(Optional.of(patient));

        ResultActions response = mockMvc.perform(get("/api/patients/{id}", patient.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(patient.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(patient.getLastName())))
                .andExpect(jsonPath("$.email", is(patient.getEmail())))
                .andExpect(jsonPath("$.phone", is(patient.getPhone())));

    }

    @Test
    @Order(4)
    public void updateEmployeeTest() throws Exception{
        patient.setFirstName("Max");
        patient.setEmail("max@gmail.com");
        given(patientService.update(patient, patient.getId())).willReturn(patient);

        ResultActions response = mockMvc.perform(put("/api/patients/{id}", patient.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(patient.getFirstName())))
                .andExpect(jsonPath("$.email", is(patient.getEmail())));
    }

    @Test
    public void deleteEmployeeTest() throws Exception{

        willDoNothing().given(patientService).delete(patient.getId());

        ResultActions response = mockMvc.perform(delete("/api/patients/{id}", patient.getId()));

        response.andExpect(status().isOk())
                .andDo(print());
    }


}
