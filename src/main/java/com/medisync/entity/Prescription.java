package com.medisync.entity;

import com.medisync.dto.request.create.PrescriptionCreateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long medicationId;

    private Long doctorId;

    private Long patientId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String filePath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Prescription fromDto(PrescriptionCreateDto dto){
        return Prescription.builder().doctorId(dto.getDoctorId())
                .patientId(dto.getPatientId()).startDate(dto.getStartDate())
                .endDate(dto.getEndDate()).medicationId(dto.getMedicationId()).build();
    }

}
