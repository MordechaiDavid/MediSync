package com.medisync.controller;

import com.medisync.dto.request.AppointmentRequestDto;
import com.medisync.dto.response.AppointmentResponseDto;
import com.medisync.entity.Appointment;
import com.medisync.service.AppointmentService;
import com.medisync.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody AppointmentRequestDto requestDto) {
        Appointment appointment = appointmentService.create(Appointment.fromAppointmentDto(requestDto));
        AppointmentResponseDto responseDto= AppointmentResponseDto.fromAppointment(appointment);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAll() {
        List<Appointment> appointments = appointmentService.getAll();
        List<AppointmentResponseDto> dtos = EntityMapper.convertList(appointments, AppointmentResponseDto::fromAppointment);
        return ResponseEntity.ok(dtos);
    }


}

