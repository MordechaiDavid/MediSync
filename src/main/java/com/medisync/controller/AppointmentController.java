package com.medisync.controller;

import com.medisync.dto.AppointmentDto;
import com.medisync.dto.UserDto;
import com.medisync.entity.Appointment;
import com.medisync.entity.User;
import com.medisync.service.AppointmentService;
import com.medisync.util.EntityMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentDto createAppointment(@RequestBody AppointmentDto dto, HttpServletRequest request) {
        Appointment appointment = appointmentService.create(Appointment.fromAppointmentDto(dto));
        return AppointmentDto.fromAppointment(appointment);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<AppointmentDto> dtos = EntityMapper.convertList(appointments, AppointmentDto::fromAppointment);
        return ResponseEntity.ok(dtos);
    }



    @GetMapping("/get-appointments-by-patient-user-id")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByPatientUserId(Long userId){
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientUserId(userId);
        List<AppointmentDto> dtos = EntityMapper.convertList(appointments, AppointmentDto::fromAppointment);
        return ResponseEntity.ok(dtos);
    }



}

