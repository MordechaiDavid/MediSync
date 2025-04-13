package com.medisync.entity;

import com.medisync.dto.request.AppointmentRequestDto;
import com.medisync.dto.response.AppointmentResponseDto;
import com.medisync.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public static Appointment fromAppointmentDto(AppointmentRequestDto dto){
        Appointment appointment =  Appointment.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentDate(dto.getAppointmentDate())
                .status(dto.getStatus())
                .build();
        return appointment;
    }
}
