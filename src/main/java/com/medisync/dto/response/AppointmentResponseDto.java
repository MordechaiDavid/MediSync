package com.medisync.dto.response;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;

    public static AppointmentResponseDto fromAppointment(Appointment appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto(
                appointment.getId(),
                appointment.getPatientId(), appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getStatus());
        return dto;
    }

}