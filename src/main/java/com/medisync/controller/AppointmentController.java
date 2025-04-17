package com.medisync.controller;

import com.medisync.dto.request.create.AppointmentCreateDto;
import com.medisync.dto.request.update.AppointmentUpdateDto;
import com.medisync.dto.request.update.UserUpdateDto;
import com.medisync.dto.response.AppointmentResponseDto;
import com.medisync.dto.response.UserResponseDto;
import com.medisync.entity.Appointment;
import com.medisync.entity.User;
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

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDto> create(@RequestBody AppointmentCreateDto requestDto) {
        Appointment appointment = appointmentService.create(Appointment.fromAppointmentDto(requestDto));
        AppointmentResponseDto responseDto= AppointmentResponseDto.fromAppointment(appointment);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentResponseDto> update(@RequestBody AppointmentUpdateDto dto){
        Appointment appointmentUpdated = appointmentService.update(Appointment.fromAppointmentDto(dto));
        return ResponseEntity.ok(AppointmentResponseDto.fromAppointment(appointmentUpdated));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAll() {
        List<Appointment> appointments = appointmentService.getAll();
        List<AppointmentResponseDto> dtos = EntityMapper.convertList(appointments, AppointmentResponseDto::fromAppointment);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/doctor/{doctorId}/all-availables")
    public ResponseEntity<List<AppointmentResponseDto>> findNextAvailableAppointmentForDoctor(@PathVariable Long doctorId){
        List<Appointment> appointments = appointmentService.findNextAvailableAppointmentForDoctor(doctorId);
        List<AppointmentResponseDto> dtos = EntityMapper.convertList(appointments, AppointmentResponseDto::fromAppointment);
        return ResponseEntity.ok(dtos);
    }



}

