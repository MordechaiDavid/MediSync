package com.medisync.dto.request;

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
public class AppointmentRequestDto {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;

}