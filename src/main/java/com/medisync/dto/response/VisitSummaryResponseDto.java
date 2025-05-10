package com.medisync.dto.response;

import com.medisync.entity.VisitSummary;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitSummaryResponseDto {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String fileName;
    private LocalDateTime createdAt;

    public static VisitSummaryResponseDto fromVisitSummary(VisitSummary vs){
        VisitSummaryResponseDto dto = new VisitSummaryResponseDto(
                vs.getId(), vs.getDoctorId(), vs.getPatientId(), vs.getFileName(),
                vs.getCreatedAt()
        );
        return dto;
    }
}
