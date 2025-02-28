package com.medisync.controller;

import com.medisync.dto.AppointmentDto;
import com.medisync.entity.Appointment;
import com.medisync.service.AppointmentService;
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
    public AppointmentDto createAppointment(@RequestBody AppointmentDto dto) {
        Appointment appointment = appointmentService.create(Appointment.fromAppointmentDto(dto));
        return AppointmentDto.fromAppointment(appointment);
    }

}

