package com.medisync.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.controller.DoctorController;
import com.medisync.dto.request.create.DoctorCreateDto;
import com.medisync.dto.response.DoctorResponseDto;
import com.medisync.entity.Doctor;
import com.medisync.enums.DoctorTitle;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.service.DoctorService;
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

@WebMvcTest(DoctorController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorControllerTest {

    DoctorCreateDto doctorCreateDto;
    Doctor doctor;

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private DoctorService doctorService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // for post tests
        doctorCreateDto = DoctorCreateDto.builder()
                .firstName("Moti")
                .lastName("David")
                .phone("0533333333")
                .password("klsdfjsdkfl")
                .email("moti@com")
                .licenseNumber("83ee8e80")
                .specialization(MedicalSpecialization.ENDOCRINOLOGY)
                .title(DoctorTitle.Prof)
                .build();

        // for get tests
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
    public void createDoctorTest() throws Exception {
        Doctor d = Doctor.fromDto(doctorCreateDto);
        d.setId(1L);
        d.setCreatedAt(LocalDateTime.now());
        given(doctorService.create(any(Doctor.class))).willReturn(d);
        ResultActions response = mockMvc.perform(post("/api/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctorCreateDto)));
        response.andDo(print()).
                andExpect(jsonPath("$.firstName",
                        is(DoctorResponseDto.fromDoctor(d).getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(DoctorResponseDto.fromDoctor(d).getLastName())))
                .andExpect(jsonPath("$.phone",
                        is(DoctorResponseDto.fromDoctor(d).getPhone())))
                .andExpect(jsonPath("$.email",
                        is(DoctorResponseDto.fromDoctor(d).getEmail())));
//                .andExpect(jsonPath("$.insuranceInfo",
//                        is(DoctorResponseDto.fromDoctor(doctor).getInsuranceInfo())))
//                .andExpect(jsonPath("$.gender",
//                        is(DoctorResponseDto.fromDoctor(doctor).getGender())))
//                .andExpect(jsonPath("$.dateOfBirth",
//                        is(DoctorResponseDto.fromDoctor(doctor).getDateOfBirth())))
//                .andExpect(jsonPath("$.id",
//                        is(DoctorResponseDto.fromDoctor(doctor).getId())));
    }

    @Test
    @Order(2)
    public void getAllDoctorTest() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        doctors.add(Doctor.builder()
                .specialization(MedicalSpecialization.ENDOCRINOLOGY)
                .licenseNumber("99939j88")
                .title(DoctorTitle.Prof)
                .password("iv8vjd9")
                .id(2L)
                .lastName("Azran")
                .firstName("Ohad")
                .phone("0544444444")
                .build());
        given(doctorService.getAll()).willReturn(doctors);
        ResultActions response = mockMvc.perform(get("/api/doctors"));
        response.andExpect(status().isOk()).
                andExpect(jsonPath("$.size()",
                        is((int) doctors.stream().map(DoctorResponseDto::fromDoctor).count())))
                .andDo(print());

    }

    @Test
    @Order(3)
    public void getByIdTest() throws Exception {
        given(doctorService.getById(doctor.getId())).willReturn(Optional.of(doctor));

        ResultActions response = mockMvc.perform(get("/api/doctors/{id}", doctor.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(doctor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(doctor.getLastName())))
                .andExpect(jsonPath("$.email", is(doctor.getEmail())))
                .andExpect(jsonPath("$.phone", is(doctor.getPhone())));

    }

    @Test
    @Order(4)
    public void updateDoctorTest() throws Exception {
        given(doctorService.getById(doctor.getId())).willReturn(Optional.of(doctor));
        doctor.setFirstName("Max");
        doctor.setEmail("max@gmail.com");
        given(doctorService.update(doctor, doctor.getId())).willReturn(doctor);

        ResultActions response = mockMvc.perform(put("/api/doctors/{id}", doctor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(doctor.getFirstName())))
                .andExpect(jsonPath("$.email", is(doctor.getEmail())));
    }

    @Test
    public void deleteDoctorTest() throws Exception {

        willDoNothing().given(doctorService).delete(doctor.getId());

        ResultActions response = mockMvc.perform(delete("/api/doctors/{id}", doctor.getId()));

        response.andExpect(status().isOk())
                .andDo(print());
    }


}
