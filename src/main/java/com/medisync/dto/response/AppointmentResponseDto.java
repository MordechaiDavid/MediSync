package com.medisync.dto.response;

import com.medisync.entity.Appointment;
import com.medisync.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentResponseDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;

    public static AppointmentResponseDto fromAppointment(Appointment appointment) {
        return AppointmentResponseDto.builder()
                .appointmentDate(appointment.getAppointmentDate())
                .id(appointment.getId())
                .doctorId(appointment.getDoctorId())
                .patientId(appointment.getPatientId())
                .status(appointment.getStatus())
                .build();
    }

}