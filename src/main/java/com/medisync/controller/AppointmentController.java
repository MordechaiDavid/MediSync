package com.medisync.controller;

import com.medisync.dto.request.create.AppointmentCreateDto;
import com.medisync.dto.request.create.AppointmentCreateDto;
import com.medisync.dto.request.update.AppointmentUpdateDto;
import com.medisync.dto.request.update.UserUpdateDto;
import com.medisync.dto.response.AppointmentResponseDto;
import com.medisync.dto.response.AppointmentResponseDto;
import com.medisync.dto.response.UserResponseDto;
import com.medisync.entity.Appointment;
import com.medisync.entity.Appointment;
import com.medisync.entity.User;
import com.medisync.service.AppointmentService;
import com.medisync.service.AppointmentService;
import com.medisync.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDto create(@RequestBody AppointmentCreateDto dto) {
        Appointment appointment = service.create(Appointment.fromDto(dto));
        return AppointmentResponseDto.fromAppointment(appointment);
    }

    @GetMapping
    public List<AppointmentResponseDto> getAll() {
        List<Appointment> appointments = service.getAll();
        return appointments.stream().map(AppointmentResponseDto::fromAppointment).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Appointment>> getById(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = service.getById(id);
        return new ResponseEntity<Optional<Appointment>>(optionalAppointment, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody Appointment appointment) {
        return new ResponseEntity<Appointment>(service.update(appointment, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>("Appointment deleted successfully with id: " + id, HttpStatus.OK);
    }

//    @GetMapping("/appointment/{appointmentId}/all-availables")
//    public ResponseEntity<List<AppointmentResponseDto>> getNextAvailablesForAppointment(@PathVariable Long appointmentId){
//        List<Appointment> appointments = appointmentService.getNextAvailableForAppointment(appointmentId);
//        List<AppointmentResponseDto> dtos = EntityMapper.convertList(appointments, AppointmentResponseDto::fromAppointment);
//        return ResponseEntity.ok(dtos);
//    }

//    @GetMapping("/patient/{patientId}/get-appointments")
//    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatient(@PathVariable Long patientId){
//        List<Appointment> appointments = appointmentService.getByPatientId(patientId);
//        List<AppointmentResponseDto> dtos = EntityMapper.convertList(appointments, AppointmentResponseDto::fromAppointment);
//        return ResponseEntity.ok(dtos);
//    }


}

