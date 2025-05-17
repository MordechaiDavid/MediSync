package com.medisync.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.controller.PatientController;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    Patient patient;

    @BeforeEach
    public void setUp(){
        patient = Patient.builder()
                .id(1L)
                .dateOfBirth(LocalDate.of(1997, 3, 28))
                .gender(Gender.MALE)
                .firstName("Moti")
                .lastName("David")
                .phone("0533333333")
                .insuranceInfo(InsuranceInfo.MACABI).build();
    }

    @Test
    @Order(1)
    public void createPatientTest() throws Exception{
        given(patientService.create(any(Patient.class))).willReturn(patient);

        ResultActions response = mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)));

        response.andDo(print()).
                andExpect(jsonPath("$.firstName",
                        is(patient.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(patient.getLastName())))
                .andExpect(jsonPath("$.phone",
                        is(patient.getPhone())));
    }

    @Test
    @Order(2)
    public void getAllPatientTest() throws Exception {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        patients.add(Patient.builder().id(2L).lastName("Azran").firstName("Ohad").gender(Gender.MALE)
                .phone("0544444444").insuranceInfo(InsuranceInfo.MACABI).dateOfBirth(LocalDate.of(1990, 4, 20))
                .build());
        given(patientService.getAll()).willReturn(patients);

        ResultActions response = mockMvc.perform(get("/api/patients"));
        response.andExpect(status().isOk()).
                andExpect(jsonPath("$.size()",
                        is(patients.size())))
                .andDo(print());


    }





}
