package com.medisync.entity;

import com.medisync.dto.request.create.AppointmentCreateDto;
import com.medisync.dto.request.update.AppointmentUpdateDto;
import com.medisync.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Appointment fromDto(AppointmentCreateDto dto) {
        return Appointment.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentDate(dto.getAppointmentDate())
                .status(dto.getStatus())
                .build();
    }

    public static Appointment fromAppointmentDto(AppointmentUpdateDto dto) {
        return Appointment.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentDate(dto.getAppointmentDate())
                .status(dto.getStatus())
                .build();
    }
}
