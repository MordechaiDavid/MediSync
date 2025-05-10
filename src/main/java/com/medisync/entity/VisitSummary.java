package com.medisync.entity;

import com.medisync.dto.request.create.VisitSummaryCreateDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;

    private Long patientId;

    private String fileName;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static VisitSummary fromVisitSummaryDto(VisitSummaryCreateDto dto){
        VisitSummary vs = VisitSummary.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .build();
        return vs;
    }

}
