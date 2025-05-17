package com.medisync.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.controller.AppointmentController;
import com.medisync.dto.request.create.AppointmentCreateDto;
import com.medisync.dto.response.AppointmentResponseDto;
import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import com.medisync.enums.Gender;
import com.medisync.enums.InsuranceInfo;
import com.medisync.enums.MedicalSpecialization;
import com.medisync.service.AppointmentService;
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

@WebMvcTest(AppointmentController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentControllerTest {

    AppointmentCreateDto appointmentCreateDto;
    Appointment appointment;

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AppointmentService appointmentService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // for post tests
        appointmentCreateDto = AppointmentCreateDto.builder()
                .appointmentDate(LocalDateTime.of(2015, 7, 20, 10, 30))
                .doctorId(1L)
                .patientId(1L)
                .status(AppointmentStatus.SCHEDULED)
                .build();

        // for get tests
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
    public void createAppointmentTest() throws Exception {
        Appointment a = Appointment.fromDto(appointmentCreateDto);
        a.setId(1L);
        a.setCreatedAt(LocalDateTime.now());
        given(appointmentService.create(any(Appointment.class))).willReturn(a);
        ResultActions response = mockMvc.perform(post("/api/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointmentCreateDto)));
        response.andDo(print()).
                andExpect(jsonPath("$.doctorId",
                        is(AppointmentResponseDto.fromAppointment(a).getDoctorId())))
                .andExpect(jsonPath("$.patientId",
                        is(AppointmentResponseDto.fromAppointment(a).getPatientId())));
//                .andExpect(jsonPath("$.status",
//                        is(AppointmentResponseDto.fromAppointment(a).getStatus())))
//                .andExpect(jsonPath("$.appointmentDate",
//                        is(AppointmentResponseDto.fromAppointment(a).getAppointmentDate())));
    }

    @Test
    @Order(2)
    public void getAllAppointmentTest() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        appointments.add(Appointment.builder()
                .id(2L)
                .appointmentDate(LocalDateTime.of(2015, 8, 4, 10, 30))
                .doctorId(1L)
                .patientId(7L)
                .status(AppointmentStatus.SCHEDULED)
                .build());
        given(appointmentService.getAll()).willReturn(appointments);
        ResultActions response = mockMvc.perform(get("/api/appointments"));
        response.andExpect(status().isOk()).
                andExpect(jsonPath("$.size()",
                        is((int) appointments.stream().map(AppointmentResponseDto::fromAppointment).count())))
                .andDo(print());

    }

    @Test
    @Order(3)
    public void getByIdTest() throws Exception {
        given(appointmentService.getById(appointment.getId())).willReturn(Optional.of(appointment));

        ResultActions response = mockMvc.perform(get("/api/appointments/{id}", appointment.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.patientId", is(appointment.getPatientId())))
                .andExpect(jsonPath("$.doctorId", is(appointment.getDoctorId())));

    }

    @Test
    @Order(4)
    public void updateAppointmentTest() throws Exception {
        given(appointmentService.getById(appointment.getId())).willReturn(Optional.of(appointment));
        appointment.setPatientId(4L);
        appointment.setDoctorId(2L);
        given(appointmentService.update(appointment, appointment.getId())).willReturn(appointment);

        ResultActions response = mockMvc.perform(put("/api/appointments/{id}", appointment.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointment)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.patientId", is(appointment.getPatientId())))
                .andExpect(jsonPath("$.doctorId", is(appointment.getDoctorId())));
    }

    @Test
    public void deleteAppointmentTest() throws Exception {

        willDoNothing().given(appointmentService).delete(appointment.getId());

        ResultActions response = mockMvc.perform(delete("/api/appointments/{id}", appointment.getId()));

        response.andExpect(status().isOk())
                .andDo(print());
    }


}
