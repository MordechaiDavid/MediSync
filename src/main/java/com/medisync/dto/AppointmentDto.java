package com.medisync.dto;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;

    public static AppointmentDto fromAppointment(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto(appointment.getId(),
                appointment.getPatientId(), appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getStatus());
        return dto;
    }

}